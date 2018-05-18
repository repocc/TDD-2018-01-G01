(ns counter-increaser (:use [clojure.string :as string]
                            [condition-processor :refer :all]
                            [state-initializer :refer :all]))

(defn get-from-data [data param] (get data (second param)) )

(defn compare-args? [keyTT args]
      (= keyTT args)  )

(defn filter-by-key [truth-table parameters]
  (filter #(compare-args? (:key %) parameters) truth-table)
  )

(defn key-is-not-present? [truth-table parameters]
  (empty? (filter-by-key truth-table parameters))
)

;;concat counters
(defn join-counters [list1 list2] (conj list1 list2))

(defn inc-n [increment]
  (fn [number] (+ number increment)))

(defn get-new-truth-table [[key-data data] step]
  (fn[pos]
      (if (= data (key-data pos)) (update pos :value (inc-n step)) pos))
  )

(defn get-step [step data past-data]
      (condition-processor/eval-condition step data (get-selected-past-data step data past-data) nil))

;;accede to truth table value, increment it if key is in map, or create a new key if it isn't
(defn inc-counter-value [truth-table parameters data step]
  (if (key-is-not-present? truth-table (map #(get-from-data data %) parameters))
    (join-counters truth-table {:key (map #(get-from-data data %) parameters) :value (get-step step data nil)})
    (map (get-new-truth-table [:key (map #(get-from-data data %) parameters)] step) truth-table))
  )

(defn transform-rule [rule truth-table]  {:type (:type rule)
                                         :name (:name rule)
                                         :params (:params rule)
                                         :condition (:condition rule)
                                         :truth-table truth-table
                                         :step (:step rule)
                                         })
(defn is-counter-rule? [rule]
      (= (:type rule) "counter"))

(defn get-new-rule [data past-data]
  (fn[rule]
    (if (and (pass-condition? (:condition rule) data past-data) (is-counter-rule? rule))
      (transform-rule rule (inc-counter-value (:truth-table rule) (:params rule) data (get-step (:step rule) data past-data)))
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
