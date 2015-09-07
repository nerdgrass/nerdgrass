(ns nerdgrass.controllers.posts
  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [nerdgrass.views.posts :as view]
            [nerdgrass.models.post :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [post]
  (when-not (str/blank? post)
    (model/create post))
  (ring/redirect "/"))

(defroutes routes
  (GET  "/" [] (index))
  (POST "/" [shout] (create post)))