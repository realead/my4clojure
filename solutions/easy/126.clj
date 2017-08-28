;;; Solution for 126. Throught the Looking Class



(def __ java.lang.Class)


;;Enter a value which satisfies the following:


(let [x __]
  (and (= (class x) x) x))


