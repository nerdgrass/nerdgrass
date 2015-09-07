(ns nerdgrass.views.posts
  (:require [nerdgrass.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn post-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 (form/label "post" "What do you want to post?")
                 (form/text-area "post")
                 (form/submit-button "Post!"))])

(defn display-posts [posts]
  [:div {:class "shouts sixteen columns alpha omega"}
   (map
    (fn [post] [:h2 {:class "shout"} (h (:body post))])
    posts)])

(defn index [posts]
  (layout/common "SHOUTER"
                 (post-form)
                 [:div {:class "clear"}]
                 (display-posts posts)))