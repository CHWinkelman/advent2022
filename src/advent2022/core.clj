(ns advent2022.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))


;; Day 1!
(def d1-input (slurp (io/resource "day1.txt")))

(defn d1-raw-to-readable
  "Takes the raw string straight from advent and replaces new lines chars
   so edn.read-string can process it"
  [raw]
  (let [replaced-nl (-> raw
                        (str/trim-newline)
                        (str/replace #"\n\n" "], [")
                        (str/replace #"\n" ","))
        adjusted-start-and-end (str "[[" replaced-nl "]]")]
    adjusted-start-and-end))

(defn d1-sum-vector
  "Takes a vector of vectors and sums each of them"
  [v]
  (map #(reduce + %) v))

(defn d1p1
  "Runs day 1 program"
  []
  (apply max (d1-sum-vector (edn/read-string (d1-raw-to-readable d1-input)))))

(defn d1p2
  "Add them"
  []
  (apply + (take-last 3
                      (sort (d1-sum-vector (edn/read-string
                                            (d1-raw-to-readable d1-input)))))))



;; Day 2!
(def d2-input (slurp (io/resource "day2.txt")))

(def d2-values
  {:win 6
   :draw 3
   :lose 0})

(def d2-shapes
  "Map of values and winners"
  ;; | Rock | Paper | Scissors |
  ;; |------+-------+----------|
  ;; |  A   |  B    |   C      |
  ;; |  X   |  Y    |   Z      |
	;; |  1   |  2    |   3      |
  {:X {:elvish :A
			 :beats :C
			 :value 1}
	 :Y {:elvish :A
		 	 :beats :B
		 	 :value 2}
	 :Z {:elvish :C
			 :beats :Z
			 :value 3}})

(defn split-whitespace
  "Split string at spaces"
  [string]
  (str/split string #" "))

(defn keywordize
  [[x y]]
  [(keyword x) (keyword y)])

(defn d2-format-input
  "Takes d2 input (which takes form of A/B/C X/Y/Z\n) and transforms to vector"
  [input]
  (let [input-vec (str/split-lines input)] ; change string to vector of strings
    (mapv #(keywordize (split-whitespace %)) ; split + keywordize string
          input-vec)))                 ; and place back in map

	(defn determine-winner
	[abc xyz]
	(let [m (get xyz d2-shapes)
				status (cond 
								(= abc (:beats m)) :win
								(= abc (:elvish m)) :draw
								:else :lose)
				score (+ (:value m) 
									(:status d2-values))]
									score))

	(defn score-all-rounds
	[rounds]
	())

(defn d2p1
  "Takes text file containing strategy guide and outputs your final score"
  [strategy]
  )
