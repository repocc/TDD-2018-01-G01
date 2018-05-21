(ns condition-processor (:require [clojure.string :as string]
                              [dsl-condition-applier]
                              [query-processor]))

;; TODO move this to dsl condition applier
(defn current? [value] (= (nth value 0) 'current))
(defn past? [value] (= (nth value 0) 'past))
(defn counter-value? [value] (= (nth value 0) 'counter-value))

(defmulti eval-condition (fn [condition data past-data state]
                           [(type condition)]))

(defmethod eval-condition [java.lang.Number] [condition data past-data state]
  condition)

(defmethod eval-condition [java.lang.String] [condition data past-data state]
  condition)

(defmethod eval-condition [java.lang.Boolean] [condition data past-data state]
  condition)

(defmethod eval-condition :default [condition data past-data state]
  (cond
    (current? condition) (get data (second condition))
    (past? condition) (get past-data (second condition))
    (counter-value? condition) (query-processor/get-query-counter-value  state (second condition) (nth condition 2))
    :else (dsl-condition-applier/apply-operation (first condition) (map #(eval-condition % data past-data state) (rest condition)))))

(defn maybe-pass? [condition data past-data]
  "Calls pass? to see if a condition is met. Return false in case of exception"
  (try
    (eval-condition condition data past-data nil)
    (catch Exception e false)))

(defn get-selected-past-data [condition data past-data]
  (first (filter #(maybe-pass? condition data %) past-data)))

;; return true if the condition is met
;; data is the current data being processed
;; past-data is collection of data previously processed
;; see the manual for the correctness of the condition expression
(defn pass-condition? [condition data past-data]
  (not (nil? (some #(maybe-pass? condition data %) past-data))))



