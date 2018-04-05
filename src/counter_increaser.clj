(ns counter-increaser (:use [clojure.string :as string]))

;;return true if data is in the map
(defn has-value [key value]
  (fn [m]
    (= value (m key))))

;;concat counters
(defn join-counters [list1 list2] (join list1 list2))

;;accede to truth table value, increment it if key is in map, or create a new key if it isn't
(defn inc-counter-value [truth-table data]
  (if (has-value :key data) (update truth-table :value inc) (join-counters truth-table {:key data :value 1}))
  )

;;should return new counter with its updated state
(defn inc-counter [state new-data]
  (update state :truth-table (inc-counter-value (state :truth-table) new-data) (state))
