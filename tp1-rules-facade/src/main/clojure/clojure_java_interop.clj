(ns clojure-java-interop (:require [data-processor :refer :all]
                                   [clojure.data.json :as json]
                                   [clojure.walk :as walk])
                         (:gen-class))

(def rules '((define-counter "email-count" []
                             true)
              (define-counter "spam-count" []
                              (current "spam"))
              (define-signal {"spam-fraction" (/ (counter-value "spam-count" [])
                                                 (counter-value "email-count" []))}
                             true)
              (define-counter "spam-important-table" [(current "spam")
                                                      (current "important")]
                              true)))

(defn -initialize []
  (json/write-str (initialize-processor rules)))

(defn -process-data [state new-data]
  (do (println (json/read-str state))
      (json/write-str (first (process-data (walk/keywordize-keys (json/read-str state)) (json/read-str new-data))) ) )
  )
