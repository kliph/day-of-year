(defproject day-of-year "1.2.1"
  :description "Show the current day of the year"
  :url "https://day-of-year.herokuapp.com"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :clean-targets ^{:protect false} [:target-path "out" "resources/public/js"]
  :min-lein-version "2.5.3"
  :repl-options {:init-ns dev.repl}
  :dependencies [[org.clojure/clojure "1.10.0-alpha4"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.8.0-alpha2"]
                 [compojure "1.6.0"]
                 [ring/ring-jetty-adapter "1.6.2"]
                 [ring/ring-ssl "0.3.0"]
                 [environ "1.1.0"]]
  :plugins [[lein-environ "1.1.0" :hooks false]
            [lein-cljsbuild "1.1.7"]
            [lein-heroku "0.5.3"]
            [lein-figwheel "0.5.13"]]
  :heroku {:app-name "day-of-year"}
  :figwheel {:css-dirs ["resources/public/css"]
             :server-port 3450}
  :uberjar-name "day_of_year.jar"
  :profiles {:dev {:dependencies [[cider/piggieback "0.4.0"]
                                  [figwheel-sidecar "0.5.18"]
                                  [binaryage/devtools "0.9.4"]]
                   :source-paths ["src" "dev"]
                   :cljsbuild {:builds [{:id "dev"
                                         :source-paths ["src"]
                                         :figwheel true
                                         :compiler {:main "day-of-year.core"
                                                    :preloads [devtools.preload]
                                                    :asset-path "js/out"
                                                    :output-to "resources/public/js/main.js"
                                                    :output-dir "resources/public/js/out"
                                                    :optimizations :none
                                                    :npm-deps {:date-fns "1.29.0"}
                                                    :install-deps true
                                                    :verbose true
                                                    :recompile-dependents true
                                                    :source-map true}}]}}
             :uberjar {:env {:production true}
                       :source-paths ["src"]
                       :prep-tasks ["compile" ["cljsbuild" "once"]]
                       :cljsbuild {:builds [{:id "production"
                                             :source-paths ["src"]
                                             :jar true
                                             :compiler {:main "day-of-year.core"
                                                        :asset-path "js/out"
                                                        :output-to "resources/public/js/main.js"
                                                        :output-dir "resources/public/js/out"
                                                        :npm-deps {:date-fns "1.29.0"}
                                                        :install-deps true
                                                        :optimizations :advanced
                                                        :pretty-print false}}]}}})
