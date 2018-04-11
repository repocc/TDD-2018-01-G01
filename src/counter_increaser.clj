(ns counter-increaser (:use [clojure.string :as string]
                      :require [condition-processor :refer :all]
                              [state-initializer :refer :all]))


(defn filter-by-key [truth-table parameters]
  (filter #(= (:key %) []) truth-table)
  )

(defn key-is-not-present? [truth-table parameters]
    (and (empty? (filter-by-key truth-table parameters)) (empty? truth-table))
  )

;;concat counters
(defn join-counters [list1 list2] (conj list1 list2))

(defn inc-value [pos]
  {
    :key (:key pos)
    :value (inc (:value pos))
  })

(defn get-new-truth-table [data]
  (fn[pos]
    (if (= data (:key pos)) (inc-value pos) pos))
)

;;accede to truth table value, increment it if key is in map, or create a new key if it isn't
(defn inc-counter-value [truth-table parameters]
  (if (key-is-not-present? truth-table parameters)
    (join-counters truth-table {:key parameters :value 1})
    (map (get-new-truth-table parameters) truth-table))
  )

(defn transform-rule [rule truth-table]  {:type (:type rule)
                                         :name (:name rule)
                                         :params (:params rule)
                                         :condition (:condition rule)
                                         :truth-table truth-table
                                         })

(defn is-counter? [rule]
  (= "counter" (:type rule))
  )

(defn get-new-rule [data past-data]
  (fn[rule]
    (if (and (pass-condition? (:condition rule) data past-data) (is-counter? rule))
      (transform-rule rule (inc-counter-value (:truth-table rule) (:params rule)))
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
