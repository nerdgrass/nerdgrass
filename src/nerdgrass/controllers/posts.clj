(ns nerdgrass.controllers.posts
  (:require
      [clostache.parser :as clostache]
      [blog.models.posts :as posts-model]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
    (str "views/posts/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {:posts (posts-model/all)}))

; (defn create
;   [post]
;   (when-not (str/blank? post)
;     (model/create post))
;   (ring/redirect "/"))

; (defroutes routes
;   (GET  "/" [] (index))
;   (POST "/" [post] (create post)))