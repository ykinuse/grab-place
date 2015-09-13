# Couchdb Place model
# NEVER USE CouchPotato view, instead use couchdb-lucene search
class Place
  include CouchPotato::Persistence
  property :city
  property :fullAdd
  property :label
  property :loc
  property :state
  property :type
  property :geometry
  property :properties

  def save
    record = new(:data)
    CouchPotato.database.save_document record
  end
end
