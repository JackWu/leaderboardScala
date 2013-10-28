package leadersboard.leadersboard.model

import net.liftweb._
import net.liftweb.util._
import net.liftweb.mongodb.{DefaultMongoIdentifier,MongoDB}
import net.liftweb.common._
import com.mongodb.Mongo
import com.mongodb.MongoOptions
import com.mongodb.ServerAddress



object MongoConfig {
  def init(mongodbHost:String,mongodbPort:Int,mongodbName:Box[String],mongodbUser:Box[String],mongodbPassword:Box[String]): Unit = {
    
    var options = new MongoOptions();
    options.connectionsPerHost = Props.getInt("mongodb.connectionsPerHost") openOr 100;
    options.threadsAllowedToBlockForConnectionMultiplier = Props.getInt("mongodb.threadsAllowedToBlockForConnectionMultiplier") openOr 10;
    
    var address = new ServerAddress(mongodbHost,mongodbPort)
    
    if(mongodbUser == Empty || mongodbPassword == Empty)
    {
    	MongoDB.defineDb(DefaultMongoIdentifier,new Mongo(address,options),mongodbName openOr "ldrly");
    }
    else
    {
      MongoDB.defineDbAuth(DefaultMongoIdentifier,new Mongo(address,options),mongodbName openOr "ldrly",mongodbUser openOr "ldrly", mongodbPassword openOr "ldrly")
    }
		
  }
  def init{
    init(Props.get("mongodb.host") openOr "127.0.0.1",Props.getInt("mongodb.port") openOr 27017,Props.get("mongodb.name"),Props.get("mongodb.user"),Props.get("mongodb.password"));
  }

}

