package bootstrap.liftweb

import net.liftweb._
import util._
import Helpers._
import common._
import http._
import js.jquery._
import leadersboard.leadersboard.controller._
import leadersboard.leadersboard.model.MongoConfig
import dispatch.BasicDispatch


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
  

    MongoConfig.init;
    
    LiftRules.dispatch.append(BasicDispatch)
  }
}
