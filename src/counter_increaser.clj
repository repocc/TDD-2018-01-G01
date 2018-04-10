(ns counter-increaser (:use [clojure.string :as string]
                      :require [condition-processor :refer :all]))


(defn data-to-table-key [data]
  (nth data 1)
  )

(defn filter-by-key [truth-table data]
  (filter #(= (:key %) (into [] (map data-to-table-key data))) truth-table)
  )

(defn key-is-not-present? [truth-table data]
  (empty? (filter-by-key truth-table data))
  )

;;concat counters
(defn join-counters [list1 list2] (conj list1 list2))

(defn get-new-truth-table [[key-data data]]
  (fn[pos]
    (if (= data (key-data pos)) (update pos :value inc) pos))
)

;;accede to truth table value, increment it if key is in map, or create a new key if it isn't
(defn inc-counter-value [truth-table data]
  (if (key-is-not-present? truth-table data)
    (join-counters truth-table {:key (into [] (map data-to-table-key data)) :value 1})
    (map (get-new-truth-table [:key (into [] (map data-to-table-key data))]) truth-table))
  )

(defn transform-rule [rule truth-table]  {:type (:type rule)
                                         :name (:name rule)
                                         :params (:params rule)
                                         :condition (:condition rule)
                                         :truth-table truth-table
                                         })

(defn get-new-rule [data past-data]
  (fn[rule]
    (if (pass-condition? (:condition rule) data past-data)
      (transform-rule rule (inc-counter-value (:truth-table rule) data))
      rule)
  )
)

(defn get-new-state [rules state] {
        :rules rules
        :past-data (:past-data state)
})

(defn process-counter [state data]
  (get-new-state (map (get-new-rule data (:past-data state)) (:rules state)) state)
)
