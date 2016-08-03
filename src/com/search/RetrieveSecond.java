package com.search;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class RetrieveSecond{
        String duration="0", rating="0", views="0",videoName="Private Video",url;
        String xml;
        Boolean flag=false;
        YoutubeVideo video,videoList; 
        public RetrieveSecond(String videoId){
        	try{
        		ConnectionDB cdb = new ConnectionDB();
                Statement st = cdb.connectJDBC();
                ResultSet rs=st.executeQuery("select * from video where id='" + videoId+ "'");
                if(rs.first()){
                	flag=true;
                    video = new YoutubeVideo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                    System.out.println("Video ID : "+ video.getVideoId());
                    System.out.println("Length of the video file is : " +video.getDuration() +" secs");
                    System.out.println("Numbers of viewers : " +video.getViews());
                    System.out.println("Rating : " +video.getRating());
                    System.out.println("Video Name : "+video.getVideoName());
                    System.out.println("Video URL :"+video.getVideoUrl());
                    System.out.println("***********************************");
                    videoList = video;
                    }else{
                    	URL url= new URL(StringConstants.GDATA_URL+videoId);
                        URLConnection urlConn = url.openConnection();
                        xml = "C:/xml/"+videoId+".xml";
                        FileOutputStream file = new FileOutputStream(xml);
                        urlConn.setDoInput(true);
                        urlConn.setUseCaches(false);
                        DataInputStream dis=null;
                        try{
                        	dis = new DataInputStream(urlConn.getInputStream());
                        	}
                            catch(IOException ioe){	
                            }
                            catch (NullPointerException npe) {
							}
                            Byte s;
                            DataOutputStream dos = new DataOutputStream(file);
                            try{
                            while((s=dis.readByte())!= 0) {
                                dos.writeByte(s);
                            }}
                            catch(EOFException eof){
                            	
                            }
                            catch (NullPointerException e) {
							}
                            file.close();
                    }
                }
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if(!flag) {
				try {
					//System.out.println("Video_ID : "+str);
                    url = "http://www.youtube.com/watch?v="+videoId;
                    FileInputStream infile = new FileInputStream(xml);
                    SAXParserFactory parserFact = SAXParserFactory.newInstance();
                    SAXParser parser = parserFact.newSAXParser();
                                
                    DefaultHandler dHandler = new DefaultHandler(){
                    boolean vdName;
                    public void startElement(String uri, String localName, String element_name, Attributes attributes)throws SAXException{
                    int length = attributes.getLength();
                    int i;
                    for ( i=0; i<length; i++) {
                    	String name = attributes.getQName(i);
                        String element_label=element_name.toString();
                        if(element_label.equals("media:title")){
                        	vdName = true;
                        	}
                        //System.out.println(label);
                        if (name.equals("seconds")) {
                            duration = attributes.getValue(i);
                            //System.out.println("Length of the video file is : "+ duration+" secs");
                            }
                        else if (name.equals("numRaters")) {
                        	views = attributes.getValue(i);
                            //System.out.println("Numbers of viewers : "+ views);
                            }
                        if (name.equals("average")) {
                        	rating = attributes.getValue(i);
                            //System.out.println("Rating : "+ rating);
                        	}
                        }
                    }
                    @Override
                    public void characters(char[] ch,int start, int length)throws SAXException {
                    	/**
                    	 * To get the name of the video using a different format as specified by the other characteristics of the video
                    	 * Here <code>vdName</code> is set
                    	 * */
                    	if(vdName){
                    		videoName = new String(ch, start, length);
                            //System.out.println("Video Name : "+videoName);
                            vdName = false;
                            }
                    	}
                    };
                    parser.parse(xml, dHandler);
                    infile.close();
                    }
				catch (Exception e) {	
				}
				try{
					ConnectionDB cdb = new ConnectionDB();
                    Statement st = cdb.connectJDBC();
                    st.executeUpdate("insert into video values  ('" + videoId+ "','" + duration + "','" + views + "','" + rating +"','" +videoName+"','" +url+"')");
                    videoList  = new YoutubeVideo(videoId, duration, views, rating, videoName, url);
                    }
				catch (Exception e) {	
				}
				}
			}
		}
        public YoutubeVideo returnSearchVideo(){
			return videoList;
		}
	}