package com.example.chat;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assistant.R;

public class IniActivity extends Activity{
	
	private static final String TABLE_NAME = "config";
	private static final String IP = "ip";
	private static final String PORT = "port";
	private static final String NAME = "name";
	
    private EditText ip,port;
    private Button nextButton;
    private String getip,getport;
    private ProgressDialog progressDialog;
    private InetSocketAddress isa = null; 
    private SQLiteDatabase db;
    private String ipstring=null,portString=null;
    private int row=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        
        ip = (EditText)findViewById(R.id.ip);
        port = (EditText)findViewById(R.id.port);
        nextButton = (Button)findViewById(R.id.next);        
        
        String sdcard = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        File path = new File(sdcard+"/A/"); //��ݿ��ļ�Ŀ¼ 
        System.out.println(path.mkdirs());
        
        db = SQLiteDatabase.openOrCreateDatabase(config.f, null);
        try {        	
            Cursor cursor = db.query("config", new String[]{"ip","port"},null,null, null, null, null);
            while(cursor.moveToNext()){
                ipstring = cursor.getString(cursor.getColumnIndex("ip"));
                portString = cursor.getString(cursor.getColumnIndex("port"));
                row++;
            }            
            ip.setText(ipstring);
            port.setText(portString);                      
            cursor.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.toString());
        }
        db.close();
        
        nextButton.setOnClickListener(new nextButtonListenner());
    }

    private Handler handler =new Handler(){
    @Override

    public void handleMessage(Message msg){
    super.handleMessage(msg);

    Toast.makeText(IniActivity.this, "connect success", Toast.LENGTH_SHORT);

    Intent intent = new Intent(IniActivity.this,IniuserActivity.class);
    startActivity(intent);
    IniActivity.this.finish();
    }
    };
    
    class nextButtonListenner implements OnClickListener{
    	 
        @Override
        public void onClick(View v) {
            try {
                // TODO Auto-generated method stub
                getip = ip.getText().toString().trim();
                getport = port.getText().toString().trim();
                if(getip=="" || getip==null || getip.equals("")){
                    Toast.makeText(IniActivity.this, "Please enter IP", Toast.LENGTH_SHORT).show();
                    ip.setFocusable(true);
                }else if(getport=="" || getport==null || getport.equals("")){
                    Toast.makeText(IniActivity.this, "Please enter Port", Toast.LENGTH_SHORT).show();
                    port.setFocusable(true);
                }else{
                progressDialog = ProgressDialog.show(IniActivity.this, "", "wait...", true, false);
                
                new Thread() {
                    @Override
                    public void run() {
                    	//Looper.prepare();
                        try {
                            Socket s = new Socket();
                            isa = new InetSocketAddress(getip,Integer.parseInt(getport)); 
                            
                            s.connect(isa,5000);                                                 
                            try {
                                //���ContentValues����
                                ContentValues values = new ContentValues();
                                //��ö����в����ֵ�ԣ����м�������ֵ��ϣ����뵽��һ�е�ֵ��ֵ�������ݿ⵱�е��������һ��
                                values.put("ip", getip);
                                values.put("port",getport);
                                db = SQLiteDatabase.openOrCreateDatabase(config.f, null); 
                               
                                String sql = "CREATE TABLE " + TABLE_NAME + " (" + IP   
                		 				+ " text, " + PORT + " INTEGER, " + NAME+" text)";   
                                db.execSQL(sql);   
                                if(row==0){
                                    db.insert("config", null, values);
                                }else{
                                    db.update("config", values ,null,null);
                                }
                                s.close();
                                db.close();
                                
                                handler.sendEmptyMessage(0);
                            } catch (Exception e) {
                                // TODO: handle exception
                               e.printStackTrace();
                            }
                             
                             
                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            //showDialog("failed,ip or port is not ok.",IniActivity.this);
                        }catch (SocketTimeoutException  e) {
                              System.out.println("runtime error");
                              //showDialog("runtime error",IniActivity.this);
                              /*Toast.makeText(getApplicationContext(), "请确定服务器处于开启或您的网络正常！",
                            		     Toast.LENGTH_SHORT).show();*/
                              System.out.println("服务器尚未启动");
                              e.printStackTrace();
                        }
                        catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            //showDialog("connect failed",IniActivity.this);
                        }
                        progressDialog.dismiss();
                        finish();
                        }
                    }.start();
                }
            } catch (Exception e) {
                Log.e("IniActivity","error");
            }
             
             
        }
         
    }

    
    /**
     * define a dialog for show the message
     * @param mess
     * @param activity
*/
/*    public void showDialog(String mess,Activity activity){
      new AlertDialog.Builder(activity).setTitle("��Ϣ")
       .setMessage(mess)
       .setNegativeButton("�_��",new DialogInterface.OnClickListener()
       {
         public void onClick(DialogInterface dialog, int which)
         {          
         }
       })
       .show();
    }*/
}