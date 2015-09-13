require 'faraday'
# Search Api::V1::PlacesController
class Api::V1::PlacesController < ApplicationController
  def initialize
    @search_url = "/_fti/local/#{CONFIG['couchdb_database']}/_design/places_search/by_full_address"
    @connection = Faraday.new(url: CONFIG['couchdb_host'])
    @connection.basic_auth(CONFIG['couchdb_user'], CONFIG['couchdb_password'])
  end

  def search
    unless params[:name] || params[:latlng]
      return render json: { message: 'Invalid Parameters' }, status: 400
    end

    res = @connection.get do |req|
      req.url @search_url
      req.params[:q] = 'bidor AND long<double>:[1 TO 150.0] AND lat<double>:[0 TO 5.0]'
      req.params[:include_docs] = true
      req.headers['Content-Type'] = 'application/json'
    end

    # pp res.body
    # pp CouchdbLucene.methods

    render json: res.body
  end
end
