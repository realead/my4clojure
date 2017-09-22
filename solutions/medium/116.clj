;;; Solution for 116. Prime Sandwitch


;; A balanced prime is a prime number which is also the mean of the primes directly before and after it in the sequence of valid primes. C
;; Create a function which takes an integer n, and returns true iff it is a balanced prime.


(defn primes-less-equal
  [n]
  (if (< n 2)
      []

      (let [lesser_primes (last (map primes-less-equal (range n)))];bottom-up to avoid stack overflow
        (if (some #(= 0 (mod n %)) lesser_primes)
            lesser_primes
            (conj lesser_primes n)
        )
      )
  )
)

;;I don't know how to be fast enough without memoization...
(def primes-less-equal (memoize primes-less-equal))



(defn balanced_prime?
  [n]
  (if (< n 3)
      false
      (let [[head tail] (split-with #(< % n) (primes-less-equal (* 2 n)))
            left (last head)
            middle (first tail)
            right (second tail)]
           (and (= n middle) (= (+ middle middle) (+ left right)))
      )
   )
)

(def __ balanced_prime?)


;tests

(= false (__ 4))
(= true (__ 563))
(= 1103 (nth (filter __ (range)) 15))
