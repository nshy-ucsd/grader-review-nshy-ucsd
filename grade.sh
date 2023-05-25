CPATH='.:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'


if [[ -f student-submission/ListExamples.java ]]
then
  echo 'Found ListExamples'
else
  echo 'Missing files: ListExamples'
  exit 1
fi

cp -r student-submission/ grading-area
cp TestListExamples.java grading-area

cd grading-area

(
    set +e
    javac -cp $CPATH ListExamples.java TestListExamples.java
    #echo 'Compiling the grading file and test files'
if [ $? -eq 0 ]
then
  echo 'Compiling succeed'
else
  echo 'Compiling failed'
  exit 1
fi
)

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-result2.txt


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point
if grep -q "OK" test-result2.txt; then
  echo "All tests passed. Grade: A"
elif grep -q "FAILURES!!!" test-result2.txt; then
  failures=$(grep -o "Failures: [0-9]*" test-result2.txt | awk '{print $2}')
  successes=$(grep -o "Tests run: [0-9]*" test-result2.txt | awk '{print $3}')
  passed=$(expr "$successes" - "$failures")
  echo "Some tests failed. Passed: $passed; Failtures: $failures"
else
  echo "Unknown test result. Grade: F"
fi
# Then, add here code to compile and run, and do any post-processing of the
# tests