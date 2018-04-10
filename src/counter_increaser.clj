(ns counter-increaser (:use [clojure.string :as string]))


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

;;should return new counter with its updated state
;;(defn inc-counter [state new-data]
;;  (update state :truth-table (inc-counter-value (state :truth-table) new-data) (state)))
