# App Controller
class ApplicationController < ActionController::API
  require 'pp'

  # include CouchPotato::Persistence
  # pp CouchPotato.database

  # def health_check
  #   # user = User.new(name: 'terry',
  #   #                 email: 'abc',
  #   #                 password: 'aaa')
  #   # CouchPotato.database.save_document user
  #   # # pp user.methods
  #   #
  #   # render json: user
  #   # data = CouchPotato.database.view Place.all(key: '2015-09-11T08:07:29Z'..'2015-10-11T08:07:29Z')
  #   # data = CouchPotato.database.view Place.label(key: 'JALAN PUTRA BIDOR')
  #
  #   data = CouchPotato.database.view Place.label(key: 'BID'..'BIDOR\ufff0')
  #   pp data
  #   render json: data
  # end
end
