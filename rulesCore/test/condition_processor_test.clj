(ns condition-processor-test
  (:require [clojure.test :refer :all]
            [condition-processor :refer :all]))

(def condition1 '(and (!= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))
(def condition2 '(or (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))
(def condition3 '(and (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))
(def condition4 '(and (= (current "sender") (past "receiver")) (includes? (past "subject") (current "subject"))))

(def true-condition '(true))

(def condition5 '(or (< (current "n") (past "n")) (= (mod (current "n") 1) 1)))

(def data1 {"sender" "ramona", "receiver" "jorge", "subject" "holo"})
(def data2 {"sender" "culkin", "receiver" "ramona", "subject" "holo"})
(def data3 {"sender" "ramona", "receiver" "scott", "subject" "carta importante"})
(def data4 {"sender" "juan", "receiver" "jorge", "subject" "holo"})

(def data-actual {"miau" false})
(def data-match {"sender" "ramona", "receiver" "scott", "subject" "holo"})

(def past-data [data1 data2 data3])

(def dataWithNumbers1 {"n" 10})
(def dataWithNumbers2 {"n" 11})

(deftest pass-condition-test
  (testing "Should pass condition given data"
    (is (= false
           (eval-condition condition1 data1 data2 nil)))
    (is (= true
           (eval-condition condition1 data1 data4 nil)))
    (is (= false
            (eval-condition condition1 data1 data2 nil)))
    (is (= false
            (eval-condition condition1 data1 data2 nil)))
    (is (= true
             (eval-condition true-condition data1 data2 nil)))
    (is (= false
            (eval-condition condition5 dataWithNumbers2 dataWithNumbers1 nil)))
    (is (= true
            (eval-condition condition5 dataWithNumbers1 dataWithNumbers2 nil)))))

(deftest get-selected-past-data-test
         (testing "Should pass condition given data"
                  (is (= nil
                         (get-selected-past-data condition1 data-actual past-data)))
                  (is (= {"sender" "culkin", "receiver" "ramona", "subject" "holo"}
                         (get-selected-past-data condition4 data-match past-data)))
                  (is (= {"sender" "ramona", "receiver" "jorge", "subject" "holo"}
                         (get-selected-past-data true-condition data-match past-data)))
                  (is (= {}
                         (get-selected-past-data true-condition data-match [{}])))
                  ))

(deftest pass-condition-test-all
         (testing "Should pass condition given data"
                  (is (= false
                         (pass-condition? condition1 data-actual past-data)))
                  (is (= true
                         (pass-condition? condition4 data-match past-data)))
                  (is (= true
                         (pass-condition? true-condition data-match past-data)))
                  (is (= true
                         (pass-condition? true-condition data-match [{}])))
                  ))
