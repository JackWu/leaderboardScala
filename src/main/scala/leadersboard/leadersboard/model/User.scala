package leaderboard.leaderboard.model
import net.liftweb.mongodb.record.MongoId
import net.liftweb.mongodb.record.MongoRecord
import net.liftweb.mongodb.record.field._
import net.liftweb.record.field._
import net.liftweb.mongodb.record.MongoMetaRecord
import net.liftweb.mongodb.JsonObject
import net.liftweb.mongodb.JsonObjectMeta
import net.liftweb.mongodb.record.{BsonRecord,BsonMetaRecord}
import net.liftweb.mongodb.BsonDSL._
import net.liftweb.json.JsonAST.JObject

class State private () extends BsonRecord[State] {
  def meta = State

  object stateName extends StringField(this, 100)
  object value extends IntField(this, 0)
}
object State extends State with BsonMetaRecord[State]

class User private () extends MongoRecord[User] with ObjectIdPk[User] {
  def meta = User

  object username extends StringField(this, 100)
  object states extends BsonRecordListField(this, State)
}
object User extends User with MongoMetaRecord[User]
{
     ensureIndex(("username" -> 1), ("unique"->"true"))
}
