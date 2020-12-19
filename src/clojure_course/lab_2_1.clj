(ns clojure-course.lab-2-1)

(def step 0.05)

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
    (+ (integral-i f (dec n)) (trapezoidal-rule-i f n))
    0))

(def memoized-integral
  (memoize integral-i))

(defn integral
  [f]
  (fn [x] (memoized-integral f (calc-n x))))




