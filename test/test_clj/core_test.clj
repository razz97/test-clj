(ns test-clj.core-test
  (:require [clojure.test :refer :all]
            [test-clj.core :refer :all]))

(deftest sma-test
  (testing "Simple moving average test"
    (is (= (sma 5 [5 1 4 3 9 21 33 9 2 10]) [22/5 38/5 14 15 74/5 15]))))

(deftest ema-test
  (testing "Exponential moving average test"
    (is (= (ema 5 [5 1 4 3 9 21]) [5 11/3 34/9 95/27 433/81 2567/243]))))

(ema-test)