;;;; Solution for 67. Prime Numbers


;; Write a function which returns the first x number of prime numbers.

(defn my-primes
   ([] (my-primes 2 []))
   ([n primes]
     (let [isnt-prime (some true? (map #(= 0 (mod n %)) primes))]
          (if isnt-prime
            (lazy-seq (my-primes (inc n) primes))
            (lazy-seq (cons n (my-primes (inc n) (conj primes n))))
          ))
   )
)

(def __ #(take % (my-primes)))

;test

(= (__ 2) [2 3])
(= (__ 5) [2 3 5 7 11])
(= (last (__ 100)) 541)
