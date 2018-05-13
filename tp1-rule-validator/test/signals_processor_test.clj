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
                     {:type "signal",
                      :name "repeated",
                      :operation (current "value")
                      :condition (= (current "value") (past "value"))}
                     )
            :past-data [{}]})

(def signal '{:type "signal",
             :name "spam-fraction",
             :operation (/ (counter-value "spam-count" []) (counter-value "email-count" []))
             :condition (true)})

(def signal-repeated '{:type "signal",
              :name "repeated",
              :operation (current "value")
              :condition (= (current "value") (past "value"))})

(def data1 {"spam" true, "receiver" "jorge", "subject" "holo", "value" "ernesto"})
(def data2 {"spam" "culkin", "receiver" "ramona", "subject" "holo", "value" "ernesto"})
(def data3 {"spam" "ramona", "receiver" "scott", "subject" "carta importante", "value" "juan"})

(def data-match {"sender" "ramona", "receiver" "scott", "subject" "holo", "value" "ernesto"})

(def past-data [data1 data2 data3])


(deftest process-signals-test
  (testing "Should return signal data"
           (is (= '{"repeated" "ernesto"}
                  (process-signal signal-repeated data-match past-data state)))
                  (is (= '{"spam-fraction" 5}
                  (process-signal signal data-match past-data state)))
           ))





