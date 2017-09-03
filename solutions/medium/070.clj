;;; Solution for 70. Word Sorting

;;Write a function that splits a sentence up into a sorted list of words. Capitalization should not affect sort order and punctuation should be ignored.



(defn word-sort
  [sentence]
  (let [words (clojure.string/split sentence, #"[ .!]")]
     (sort-by clojure.string/upper-case words)
   )
)

(def __ word-sort)


; tests

(= (__  "Have a nice day.")
   ["a" "day" "Have" "nice"])

(= (__  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])

(= (__  "Fools fall for foolish follies.")

   ["fall" "follies" "foolish" "Fools" "for"])
