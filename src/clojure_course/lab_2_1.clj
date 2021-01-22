(ns clojure-course.lab-2-1)

(def step 0.005)

(defn trapezoidal-rule
  [fa fb]
  (/ (* (+ fa fb) step) 2.0))

(defn trapezoidal-rule-i
  [f i]
  (trapezoidal-rule (f (* i step)) (f (* (dec i) step))))

(defn calc-n
  [interval]
  (int (/ interval step)))

(defn integral-i
  [f n]
  (if (> n 0)
    (let [partial-sum (trapezoidal-rule-i f n)]
      (+ (integral-i f (dec n)) partial-sum))
    0))

(def memoized-integral
  (memoize integral-i))

(defn integral
  [f]
  (fn [x] (let [steps (calc-n x)]
            (memoized-integral f steps)
            )))




