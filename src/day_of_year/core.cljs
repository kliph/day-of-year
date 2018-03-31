(ns day-of-year.core
  (:require [reagent.core :as r]
            [goog.dom]
            date-fns))

(def by-id goog.dom.getElement)

(defn app-container []
  [:section {}
   [:div.value {}
    (date-fns/format (js/Date.) "DDDD")]])

(r/render-component [app-container] (by-id "app"))

(when (exists? js/navigator.serviceWorker)
  (-> js/navigator
      .-serviceWorker
      (.register "/sw.js")
      (.then #(js/console.log "Server worker registered."))))
