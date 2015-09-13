# User
class User
  include CouchPotato::Persistence
  property :name
  property :email
  property :password

  def save
  end
end
