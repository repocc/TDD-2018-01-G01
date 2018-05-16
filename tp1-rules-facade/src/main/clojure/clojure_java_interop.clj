(ns clojure-java-interop (:use [data-processor :refer :all])
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
      (str (initialize-processor rules)))
