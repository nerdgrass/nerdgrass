(ns nerdgrass.models.migration
  (:require [clojure.java.jdbc :as sql]
            [nerdgrass.models.post :as post]))

(defn migrated? []
  (-> (sql/query post/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='posts'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands post/spec
                        (sql/create-table-ddl
                         :posts
                         [:id :serial "PRIMARY KEY"]
                         [:body :varchar "NOT NULL"]
                         [:created_at :timestamp
                          "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))