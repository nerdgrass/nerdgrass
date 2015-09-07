(defproject nerdgrass "0.0.1"
  :description "Nerdgrass Revival, blog extraordinaire"
  :url "https://github.com/nerdgrass/nerdgrass"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.2"]
                 [hiccup "1.0.5"]]
  :main ^:skip-aot nerdgrass.core
  :uberjar-name "nerdgrass-standalone.jar"
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler nerdgrass.core/application
         :init nerdgrass.models.migration/migrate}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                                  [ring-mock "0.1.5"]]}
             :uberjar {:aot :all}})