(ns advent2022.day-1
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(defn d1p1
  "Runs day 1 program"
  []
  (apply max (sum-vector (edn/read-string (raw-to-readable day1-input)))))

(defn d1p2
  []
  (apply + (take-last 3 (sort (sum-vector (edn/read-string (raw-to-readable day1-input)))))))

(def day1-input (slurp (io/resource "day1.txt")))

;; (defn day1
;;   [input]
;;                                         ;(apply (apply str/split-lines (str/split #"\n\n")))
;;   (let [split-elves (str/split input #"\n\n")
;;         split-again-elves (map str/split-lines split-elves)
;;         total-split-elves (map )]
;;     total-split-elves))


;; 5529\n1561\n\n6276\n1284\n1223\n

(defn raw-to-readable
  "Takes the raw string straight from advent and replaces new lines chars
   so edn.read-string can process it"
  [raw]
  (let [replaced-nl (-> raw
                        (str/trim-newline)
                        (str/replace #"\n\n" "], [")
                        (str/replace #"\n" ","))
        adjusted-start-and-end (str "[[" replaced-nl "]]")]
    adjusted-start-and-end))

(defn sum-vector
  "Takes a vector of vectors and sums each of them"
  [v]
  (map #(reduce + %) v))
