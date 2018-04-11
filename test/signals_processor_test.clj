(ns signals-processor-test
  (:require [clojure.test :refer :all]
            [signals-processor :refer :all]))

(def state {:rules '({:type "counter",
                      :name "email-count",
                      :params [],
                      :condition true,
                      :truth-table ({:key [], :value 8})}
                     {:type "counter",
                      :name "spam-count",
                      :params [],
                      :condition (current "spam"),
                      :truth-table ({:key [], :value 40})}
                     {:type "signal",
                      :name "spam-fraction",
                      :operation (/ (counter-value "spam-count" []) (counter-value "email-count" []))
                      :condition (true)}
                     )
            :past-data [{}]})

(def signal '{:type "signal",
             :name "spam-fraction",
             :operation (/ (counter-value "spam-count" []) (counter-value "email-count" []))
             :condition (true)})

(def data1 {"spam" true, "receiver" "jorge", "subject" "holo"})
(def data2 {"spam" "culkin", "receiver" "ramona", "subject" "holo"})
(def data3 {"spam" "ramona", "receiver" "scott", "subject" "carta importante"})

(def data-match {"sender" "ramona", "receiver" "scott", "subject" "holo"})

(def past-data [data1 data2 data3])


(deftest process-signals-test
  (testing "Should return signal data"
           (is (= 5
                  (process-signal signal data-match past-data state)))
           ))



