module Api
	module V1 
		class DishesController < ApplicationController
			respond_to :json
			protect_from_forgery
			skip_before_action :verify_authenticity_token, if: :json_request?
			
			def index
				respond_with Order.all
			end

			def create
				respond_with Order.create(dish_params_create)
			end

			def destroy
				respond_with Order.find(params[:id]).destroy
			end

			def update 
				respond_with Order.update(params[:id],dish_params_update)
			end

			protected
  			
  				def json_request?
    				request.format.json?
  				end

  				def dish_params_create
  					params.require(:order).permit(:id,:dish_id,:table_id,:total,:is_payed)
  				end

  				def dish_params_update
  					params.require(:order).permit(:dish_id,:table_id,:total,:is_payed)
  				end

		end
	end
end