package QuickSortComparisonsPQ2

import scala.collection.mutable

//import inversionsNumberPQ1

/**
 * Created by gluk-alex on 7/17/15.
 */
object PivotingStrategies {

  /*if path & name OK this is enough*/
  def getInput(
                filename: String =
                "QuickSort.txt",
                filePath: String =
                "/media/gluk-alex/GDI/Java/Scala/sbt/projects/" +
                  "stanfordAlgorithmsDesignAndAnalysis1/src/test/scala/" +
                  "testQuickSortComparisons/"
                ): Iterator[String] = {
    //"/media/gluk-alex/GDI/Java/Scala/sbt/projects/
    // stanfordAlgorithmsDesignAndAnalysis1/src/
    // test/scala/testQuickSortComparisons"
    /*val filename =
      "QuickSort.txt"
    val filePath =
      "/media/gluk-alex/GDI/Java/Scala/sbt/projects/" +
    "stanfordAlgorithmsDesignAndAnalysis1/src/test/scala/" +
    "testQuickSortComparisons/"*/
    /*val currFile = Source
      .fromFile(filePath + filename)*/
    val currFileLines =
      scala.io.Source
      .fromFile(filePath + filename)
      .getLines()
    /*return value*/
    currFileLines
  }

  /*
  Question 1
  GENERAL DIRECTIONS:
  Download the text file
  /media/gluk-alex/GDI/Java/Scala/sbt/projects/
  stanfordAlgorithmsDesignAndAnalysis1/src/test/scala/
  testQuickSortComparisons/
  QuickSort.txt.

  The file contains
  all of the integers between 1 and 10,000 (inclusive, with no repeats)
  in unsorted order.
  The integer in the i-th row of the file
  gives you
  the i-th entry of an input array.
  DONE
  Your task is to
  compute
  the `total number of comparisons` used to
  sort the given input file by QuickSort.
  As you know,
  the `number of comparisons` depends
  on which elements are chosen as `pivots`,
  so
  we'll ask you to
  explore three different `pivoting rules`.
  You should
  not count comparisons one-by-one.
  Rather,
  when
  there is
  a recursive call on a subarray of length 'm',
  you should
  simply add 'm−1' to your running `total of comparisons`.
  (This is because
  the `pivot` element is
  compared to each of the other 'm−1' elements in
  the subarray in this recursive call.)

  WARNING:
  >The Partition subroutine can
    be implemented in several different ways, and
    different implementations can
    give you differing numbers of comparisons.
    For this problem,
    you should
    implement the Partition subroutine exactly as
    it is described in the video lectures
    (otherwise you might get the wrong answer).

  DIRECTIONS FOR THIS PROBLEM:
  >For the first part of the programming assignment,
    you should
    always use
    the `first element` of the array as the `pivot element`.

  HOW TO GIVE US YOUR ANSWER:
  >Type the numeric answer in the space provided.
  So if your answer is '1198233847', then
  just type '1198233847' in the space provided
  without any space / commas / other punctuation marks.
  You have 5 attempts to get the correct answer.
  (We do not require you to submit your code, so
  feel free to use the programming language of your choice,
  just type the numeric answer in the following space.)
   */

  /*compare must precede*/
  /*? suitable for other Sequence too ?*/
  def swapArrayElements(
                         sourceArray: Array[Int],
                         indexOfLesser: Int,
                         indexOfGreater: Int
                         ): Unit = {
    val lesserElem: Int = sourceArray(indexOfLesser)
    val greaterElem: Int = sourceArray(indexOfGreater)
    /*side effect*/
    sourceArray(indexOfLesser) = greaterElem
    sourceArray(indexOfGreater) = lesserElem
  }

  def placePivotAtStart(
                         sourceArray: Array[Int],
                         headIndex: Int,
                         pivotIndex: Int
                         ): /*Unit*/ Array[Int] = {
    val pivot: Int =
      sourceArray(pivotIndex)

    if (sourceArray(headIndex) == pivot) {
      /*we good, nothing to do*/
    } else /*if (sourceArray.head > pivot)*/ {
      /*swap*/
      swapArrayElements(
                         sourceArray: Array[Int],
                         indexOfLesser = headIndex,
                         indexOfGreater = pivotIndex
                       )
    }
    /*return value*/
    sourceArray
  }

  /*reserve additional extra space*/
  def PivotingArrayToEmptyClone(
                                 sourceArray: Array[Int],
                                 sourceArrayLenght: Int,
                                 pivotIndex: Int
                                 ): Array[Int] = {
    /*base case when 'pivotIndex == 0' or `pivot element` is 'head'*/
    /*so, reduce to that case*/
    val pivot: Int =
      sourceArray(pivotIndex)

    if (sourceArray.head == pivot) {
      /*we good, nothing to do*/
    } else /*if (sourceArray.head > pivot)*/ {
      /*swap*/
      swapArrayElements(
                         sourceArray: Array[Int],
                         indexOfLesser = 0,
                         indexOfGreater = pivotIndex
                       )
    }

    val pivotedArray: Array[Int] =
      new Array[Int](sourceArrayLenght)
    /*side effects*/
    /*increasing up to Array Upper bound*/
    var freeLeftIndex = 0
    /*decreasing down to Array Lower bound*/
    var freeRightIndex = sourceArrayLenght - 1
    /*bucket up*/
    for (i <- sourceArray.indices /*if i>0*/ ) {
      //if (sourceArray(i)<=pivot) {
      if (sourceArray(i) < pivot) {
        /*go to next free left index*/
        pivotedArray(freeLeftIndex) = sourceArray(i)
        freeLeftIndex += 1
      } else if (sourceArray(i) > pivot) {
        /*go to next free right index*/
        pivotedArray(freeRightIndex) = sourceArray(i)
        freeRightIndex -= 1
      } else /*if (sourceArray(i)==pivot)*/ {
        /*go to next free left index at last step*/
      }
    }
    pivotedArray(freeLeftIndex) = pivot
    /*return value*/
    //(lessThanPivot, pivot, greaterThanPivot)
    pivotedArray
  }

  /*
  be sure to
  implement Partition exactly
  as described in the video lectures
  (including
  exchanging the pivot element with
  the first element
  just before the main Partition subroutine).
   */
  /*constant space - no additional extra space needed*/
  /* ? prerequisites - pivot at 'startingIndex' ?*/
  /*
  rearrange 'sourceArray: Array[Int]'
  at most at three parts:
  from 'startingIndex' to 'lessTanPivot',
  pivot,
  from 'greaterThanPivot' to 'endingIndex'
  return:
  'pivotIndex'
  */
  case class PivotingResults(
                              sortedArray: Array[Int],
                              pivotIndex: Int)

  def PivotingArrayInPlace(
                            sourceArray: Array[Int],
                            startingIndex: Int,
                            endingIndex: Int,
                            /*mast be within*/
                            pivotIndex: Int
                            //): Array[Int] = {
                            ): PivotingResults = {
    assume(pivotIndex >= startingIndex && pivotIndex <= endingIndex,
           "'pivotIndex' mast be within range")

    placePivotAtStart(
                       sourceArray: Array[Int],
                       headIndex = startingIndex,
                       pivotIndex: Int
                     )
    val pivot: Int = sourceArray(startingIndex)
    /*side effects*/
    /*? must point to the next after it ?*/
    /*next after possible final pivot position / index */
    //var lessThanPivotEndIndex: Int =
    var firstGreaterThanPivotIndex: Int =
      startingIndex + 1
    /*at first step they must be equal / the same*/
    /*? only needed to set a start ?*/
    /*? must point to the next after it ?*/
    /*val greaterThanPivotEndIndex: Int =
      lessThanPivotEndIndex*/
    // + 1

    //for (i <- greaterThanPivotEndIndex to endingIndex) {
    for (i <- firstGreaterThanPivotIndex to endingIndex) {
      if (sourceArray(i) < pivot) {
        /*? may be check for redundant swaps in first / initial steps?*/
        //if (lessThanPivotEndIndex < i) {}
        swapArrayElements(
                           sourceArray,
                           firstGreaterThanPivotIndex,
                           /*greaterThanPivotEndIndex*/ i)
        firstGreaterThanPivotIndex += 1
      } else if (sourceArray(i) > pivot) {
        /*do nothing*/

      } else /*if(sourceArray(i)==pivot)*/ {
        /*?do nothing?*/
        /*assume that elements are distinct*/

      }
    }
    /*swap pivot from head to the right place*/
    /*if not the same*/
    if (firstGreaterThanPivotIndex - 1 > startingIndex) {
      swapArrayElements(
                         sourceArray,
                         indexOfLesser =
                           firstGreaterThanPivotIndex - 1,
                         //startingIndex,
                         /*
                         ? swap with
                         actual last element lesser then pivot value ?
                          */
                         indexOfGreater =
                           startingIndex
                         //firstGreaterThanPivotIndex - 1
                       )
    }
    /*return value*/
    //sourceArray
    PivotingResults(sourceArray, firstGreaterThanPivotIndex - 1)
  }

  /*use `Randomized Selection` for optimal / best pivot*/
  /*return i-th smallest element from distinct sequence*/
  def orderStatisticRandomSelection(
                                     sourceSeq: /*Seq*/ Array[Int],
                                     smallestOrder: Int,
                                     startIndex: Int,
                                     endIndex: Int
                                     ): Int = {
    /*as method is recursive 'assume' fails in later iterations*/
    /*assume(
            smallestOrder >= 0 &&
              smallestOrder < sourceSeq.length,
    s"'smallestOrder'$smallestOrder must be " +
      s"within '0' & 'sourceSeq.length'${sourceSeq.length} or indices"
          )*/
    if (sourceSeq.length == 1) {
      /*return value*/
      sourceSeq
      .head
    } else {
      assume(
              sourceSeq.nonEmpty,
              s"'sourceSeq' must be " +
                s"'nonEmpty'"
            )
      /*Randomized Selection*/
      val loBound: Int =
      //0
        startIndex
      val hiBound: Int =
      //sourceSeq.length - 1
        endIndex
      val pivotIndex: Int =
        randomIntWithinInterval(
                                 loBound: Int,
                                 hiBound: Int)
      /*val pivot: Int =
        sourceSeq(pivotIndex)*/
      assume(
              pivotIndex >= startIndex &&
                pivotIndex <= endIndex,
              s"'pivotIndex'$pivotIndex must be " +
                s"within 'startIndex'$startIndex & 'endIndex'$endIndex"
            )
      val PivotingResults(
      pivotedArray,
      newPivotIndex) =
        PivotingArrayInPlace(
                              sourceArray =
                                sourceSeq.toArray,
                              startingIndex = loBound,
                              endingIndex = hiBound,
                              pivotIndex = pivotIndex
                            )

      if (newPivotIndex == smallestOrder) {
        /*return value*/
        /*`lucky` case*/
        //pivotIndex
        //sourceSeq(newPivotIndex)
        pivotedArray(newPivotIndex)
      } else if (newPivotIndex > smallestOrder) {
        /*reduce to the left part*/
        /*recursion*/
        orderStatisticRandomSelection(
                                       pivotedArray,
                                       //.take(newPivotIndex),
                                       smallestOrder,
                                       startIndex = startIndex,
                                       endIndex = newPivotIndex - 1
                                     )
      } else /*if (pivotIndex < smallestOrder)*/ {
        /*reduce to the right part*/
        /*recursion*/
        orderStatisticRandomSelection(
                                       pivotedArray,
                                       //.drop(newPivotIndex + 1),
                                       smallestOrder - newPivotIndex,
                                       startIndex = newPivotIndex + 1,
                                       endIndex = endIndex
                                     )
      }
    }
  }

  /*
  also
  using 2 `heaps` partitioning:
  >1-st one with `extract-max`
  >2-nd one with `extract-min`
   balanced by `half` of number of `elements` in each (as `invariant`),
   so
   with `adding` / every time when new `element`
   both heaps return `median`
   {[1,2,3][4,5,6]}
   {[1,2,3][4,5,6,7]}
   {[1,2,3,4][5,6,7]}
   to keep `invariant` needed to
   redistribute unbalanced `elements` between `heaps`
   */
  class HeapsMedian {
    val heapMax = new
        mutable.PriorityQueue()(
                                 //implicit ord: Ordering[A]
                                 Ordering[Int]
                               )
    /*heapMax += (5,8,3,1,0,7,4,6)
    heapMax.head
    heapMax.dequeue()*/
    val heapMin = new
        mutable.PriorityQueue()(
                                 Ordering[Int].reverse
                               )

    def add(elem: Int): Unit = {
      /*val minMedian: Option[Int] =
        heapMax
        .headOption*/
      val maxMedian: Option[Int] =
        heapMin
        .headOption
      /*side effect*/
      if (
        maxMedian.isDefined &&
          maxMedian.get < elem
      ) {
        heapMin += elem//.toString.toInt
      } else {
        heapMax += elem //.toString.toInt
      }

      val heapMaxLength: Int =
        heapMax
        .length
      val heapMinLength: Int =
        heapMin
        .length

      if (
        heapMaxLength > heapMinLength /* + 1*/ &&
          heapMaxLength > 1
      ) {
        val unbalancedElem: Int =
          heapMax
          .dequeue()
        /*side effect*/
        heapMin += unbalancedElem
      }else if (
              heapMaxLength +1 < heapMinLength &&
                heapMinLength > 1
            ) {
        val unbalancedElem: Int =
          heapMin
          .dequeue()
        /*side effect*/
        heapMax += unbalancedElem
      } else {
        /*do nothing*/
      }
    }
    def addElem(elems: Int*): HeapsMedian = {
      /*side effect*/
      for (elem<-elems) {
        val heapMaxLength: Int = heapMax.length
        /*side effect*/
        add(elem)
      }
      /*return value*/
      this
    }

    /*heaps must be balanced by size &
    as invariant
    all 'heapMax's elements must be less than or equal to any 'heapMin's element
    * */
    def addElems(elems: Seq[Int]): HeapsMedian = {
      /*side effect*/
      for (elem<-elems/*.toSeq*/) {
        add(elem)
      }
      /*return value*/
      this
    }

    def getMedians: Option[(Int, Int)] = {
      val minMedian: Option[Int] =
        heapMax
        .headOption
      val maxMedian: Option[Int] =
        heapMin
        .headOption
      /*return value*/
      if (minMedian.isEmpty && maxMedian.isEmpty) {
        None
      } else if (minMedian.isDefined && maxMedian.isEmpty) {
        Some(minMedian.get, minMedian.get)
      } else if (minMedian.isEmpty && maxMedian.isDefined) {
        Some(maxMedian.get, maxMedian.get)
      } else /*if (minMedian.isDefined && maxMedian.isDefined)*/ {
        Some(minMedian.get, maxMedian.get)
      }
    }
  }

  /*return value*/
  def medianOfFive(
                    upToFiveElem: Array[Int]
                    ): Int = {
    assume(upToFiveElem.nonEmpty,
           s"'upToFiveElem' must be 'nonEmpty'")
    /*return value*/
    if (upToFiveElem.length == 1) {
      /*return value*/
      upToFiveElem.head
    } else if (upToFiveElem.length == 2) {
      if (upToFiveElem.head > upToFiveElem.tail.head) {
        upToFiveElem.tail.head
      } else {
        upToFiveElem.head
      }
    } else if (upToFiveElem.length == 3) {
      val head = upToFiveElem.head
      val median = upToFiveElem.tail.head
      val last = upToFiveElem.last

      if (head > median) {
        if (head > last) {
          if (last > median) {
            last
          } else /*if (last <= median)*/ {
            median
          }
        } else /*if (head <= last)*/ {
          head
        }
      } else /*if (head <= median)*/ {
        if (median > last) {
          if (last > head) {
            last
          } else /*if (last <= median)*/ {
            head
          }
        } else /*if (median <= last)*/ {
          last
        }
      }
      /*} else if (upToFiveElem.length == 4){
        -1*/
    } else /*if (upToFiveElem.length == 5)*/ {
      /*general case*/
      val arrayLength: Int =
        upToFiveElem.length

      upToFiveElem
      .sorted
      .apply(arrayLength / 2 - 1 + arrayLength % 2)
    }
  }

  /*return `median-of-medians` value ? & index ?*/
  def medianOfMedians(
                       sourceArray: Array[Int]
                       ): Int = {
    /*
    {1,2,3,4,5,6,7,8,9}
    someSeq
    .sliding(5, 5)
    .toList
    {1,2,3,4,5}{6,7,8,9}
    if size - even pick tail of 1-st half
    if size - odd pick head of 2-nd half
    {1,2,(3),4,5}{6,(7),8,9}
    median(3,7)=3
     */
    if (sourceArray.length > 5) {
      val slidingByFive: /*Array*/ Iterator[Array[Int]] =
        sourceArray
        .sliding(5, 5)

      val mediansArray =
        for (elem <- slidingByFive) yield
        medianOfFive(elem)

      /*recursion*/
      medianOfMedians(
                       mediansArray.toArray
                     )
    } else {
      /*return value*/
      medianOfFive(sourceArray)
    }
  }

  /*return `median-of-medians` value ? & index ?*/
  def pivotDeterministicSelection(
                                   sourceArray: Array[Int],
                                   orderStatisticIndex: Int /*,
                              startIndex: Int,
                              endIndex: Int*/
                                   //,
                                   /*accum*/
                                   //medians: List[Int] = List.empty[Int]
                                   ): /*Map[Int,*/ Int /*]*/ = {
    if (sourceArray.length == 1) {
      /*return value*/
      sourceArray
      .head
    } else {
      assume(
              sourceArray.nonEmpty,
              s"'sourceArray' must be " +
                s"'nonEmpty'"
            )
      val pivot: Int =
        medianOfMedians(sourceArray)
      val (lessThanPivot, pivotAndAllGreater) =
        sourceArray
        .partition(_ <= pivot)
      //val pivotedArray = lessThanPivot +: pivotAndAllGreater
      val newPivotIndex =
        lessThanPivot.length // + 1


      if (newPivotIndex == orderStatisticIndex) {
        /*return value*/
        /*`lucky` case*/
        pivot
      } else if (newPivotIndex > orderStatisticIndex) {
        /*reduce to the left part*/
        /*recursion*/
        pivotDeterministicSelection(
                                     lessThanPivot,
                                     //.take(newPivotIndex),
                                     orderStatisticIndex /*,
                                       startIndex = startIndex,
                                       endIndex = newPivotIndex - 1*/
                                   )
      } else /*if (pivotIndex < smallestOrder)*/ {
        /*reduce to the right part*/
        /*recursion*/
        pivotDeterministicSelection(
                                     pivotAndAllGreater.tail,
                                     orderStatisticIndex - newPivotIndex /*,
                                       startIndex = newPivotIndex + 1,
                                       endIndex = endIndex*/
                                   )
      }
    }
  }

  /*when deterministic use `median-of-medians` for optimal / best pivot*/
  /*return i-th smallest element from distinct sequence*/
  def orderStatisticDeterministicSelection(
                                            sourceSeq: /*Seq*/ Array[Int],
                                            smallestOrder: Int,
                                            startIndex: Int,
                                            endIndex: Int
                                            ): Int = {
    if (sourceSeq.length == 1) {
      /*return value*/
      sourceSeq
      .head
    } else {
      assume(
              sourceSeq.nonEmpty,
              s"'sourceSeq' must be " +
                s"'nonEmpty'"
            )
      /*Randomized Selection*/
      val loBound: Int =
      //0
        startIndex
      val hiBound: Int =
      //sourceSeq.length - 1
        endIndex
      val pivotIndex: Int =
        randomIntWithinInterval(
                                 loBound: Int,
                                 hiBound: Int)
      /*val pivot: Int =
        sourceSeq(pivotIndex)*/
      assume(
              pivotIndex >= startIndex &&
                pivotIndex <= endIndex,
              s"'pivotIndex'$pivotIndex must be " +
                s"within 'startIndex'$startIndex & 'endIndex'$endIndex"
            )
      val PivotingResults(
      pivotedArray,
      newPivotIndex) =
        PivotingArrayInPlace(
                              sourceArray =
                                sourceSeq.toArray,
                              startingIndex = loBound,
                              endingIndex = hiBound,
                              pivotIndex = pivotIndex
                            )

      if (newPivotIndex == smallestOrder) {
        /*return value*/
        /*`lucky` case*/
        //pivotIndex
        //sourceSeq(newPivotIndex)
        pivotedArray(newPivotIndex)
      } else if (newPivotIndex > smallestOrder) {
        /*reduce to the left part*/
        /*recursion*/
        orderStatisticRandomSelection(
                                       pivotedArray,
                                       //.take(newPivotIndex),
                                       smallestOrder,
                                       startIndex = startIndex,
                                       endIndex = newPivotIndex - 1
                                     )
      } else /*if (pivotIndex < smallestOrder)*/ {
        /*reduce to the right part*/
        /*recursion*/
        orderStatisticRandomSelection(
                                       pivotedArray,
                                       //.drop(newPivotIndex + 1),
                                       smallestOrder - newPivotIndex,
                                       startIndex = newPivotIndex + 1,
                                       endIndex = endIndex
                                     )
      }
    }
  }

  /*? assume 'sourceSeq.nonEmpty' ?*/
  def ChoosePivotIndex(
                        sourceSeq: Array[Int],
                        sourceSeqLenght: Int
                        ): Int = {
    /*return value*/
    sourceSeqLenght
  }

  /*return 'pivotIndex'*/
  def ChooseFirstElementAsPivot(
                                 sourceSeq: Array[Int],
                                 sourceSeqLenght: Int,
                                 startIndex: Int = 0,
                                 endIndex: Int = 0
                                 ): Int = {
    if (
      sourceSeq.isEmpty ||
        sourceSeqLenght < 1 ||
        /*? safe if falling through ?*/
        endIndex - startIndex < 1
    ) {
      /*return value*/
      /*assume that all values must be positive*/
      -1
    } else {
      /*return value*/
      //sourceSeq.head
      //0
      startIndex
    }
  }

  /*return 'pivotIndex'*/
  def ChooseLastElementAsPivot(
                                sourceSeq: Array[Int],
                                sourceSeqLenght: Int,
                                startIndex: Int = 0,
                                endIndex: Int = 0
                                ): Int = {
    if (
      sourceSeq.isEmpty ||
        sourceSeqLenght < 1 ||
        /*? safe if stops in first conditions ?*/
        endIndex - startIndex < 1
    ) {
      /*return value*/
      /*assume that all values must be positive*/
      -1
    } else {
      /*return value*/
      //sourceSeq.last
      //sourceSeqLenght - 1
      //startIndex + sourceSeqLenght - 1
      //or just
      endIndex
    }
  }

  /*return 'pivotIndex'*/
  def ChooseMedianOfThreeAsPivot(
                                  sourceSeq: Array[Int],
                                  //sourceSeqLenght: Int,
                                  firstSeqIndex: Int,
                                  lastSeqIndex: Int
                                  ): Int = {
    /*for even '4 5 6 7' 'middleIndex = 2' 'elem = 5'*/
    /*'4/2' or 'length/2'*/
    /*for odd '3 4 5 6 7' 'middleIndex = 2' 'elem = 5'*/
    /*'5/2' or 'length/2'*/
    /*? if firstSeqIndex == lastSeqIndex then == middleIndex ? */
    //0,(1,[2],3,4),5
    //(1) + (4-1)/2
    //0,1,(2,3,[4],5,6),7
    //(2) + (6-2)/2
    //([0])
    //(0) + (0-0)/2
    //([0],1)
    //(0) + (1-0)/2
    //0,1,2,3,([4],5),6,7
    //(4) + (5-4)/2
    //val middleIndex: Int =
    val medianIndex: Int =
      if (
        sourceSeq.isEmpty ||
          /*one element or less*/
          lastSeqIndex <= firstSeqIndex
      ) {
        //firstSeqIndex
        /*return value*/
        /*assume that all values must be positive*/
        -1
      } else if (
             /*special case:*/
             /*only two elements*/
               lastSeqIndex == firstSeqIndex + 1
             ) {
        /*return value*/
        /*must be min of two*/
        if (sourceSeq(firstSeqIndex) <= sourceSeq(lastSeqIndex)) {
          firstSeqIndex
        } else {
          lastSeqIndex
        }
        //sourceSeq(firstSeqIndex).min(sourceSeq(lastSeqIndex))
      } else {
        val middleIndex: Int =
          firstSeqIndex + (lastSeqIndex - firstSeqIndex) / 2

        /*? may be the same ? */
        val (firstMiddleMin, firstMiddleMax): (Int, Int) =
          if (
            sourceSeq(middleIndex) >= sourceSeq(firstSeqIndex)
          ) {
            //(sourceSeq(firstSeqIndex), sourceSeq(middleIndex))
            (firstSeqIndex, middleIndex)
          } else {
            //(sourceSeq(middleIndex), sourceSeq(firstSeqIndex))
            (middleIndex, firstSeqIndex)
          }
        //val median: Int =
        //val medianIndex: Int =
        //List(firstSeqIndex,middleIndex,lastSeqIndex).sorted.apply(1)
        /*return value*/
        if (
        //firstMiddleMax <= sourceSeq(lastSeqIndex)
          sourceSeq(firstMiddleMax) <= sourceSeq(lastSeqIndex)
        ) {
          firstMiddleMax
        } else /*if (
        firstMiddleMax > sourceSeq(lastSeqIndex)*/ {
          if (
          //sourceSeq(lastSeqIndex) >= firstMiddleMin
            sourceSeq(lastSeqIndex) >= sourceSeq(firstMiddleMin)
          ) {
            //sourceSeq(lastSeqIndex)
            lastSeqIndex
          } else {
            firstMiddleMin
          }
        }
      }
    /*return value*/
    //median
    //sourceSeq(medianIndex)
    medianIndex
  }

  def ChoosePivotAsHead(
                         sourceSeq: Array[Int],
                         sourceSeqLenght: Int
                         ): Int = {
    if (sourceSeq.isEmpty) {
      /*return value*/
      /*assume that all values must be positive*/
      -1
    } else {
      /*return value*/
      sourceSeq.head
    }
  }

  /*both bound inclusive*/
  def randomIntWithinInterval(
                               loBound: Int,
                               hiBound: Int): Int = {
    /*or meaningless & not work*/
    assume(loBound <= hiBound, s"$loBound must be <= $hiBound")
    val randomDouble: Double =
      scala.math.random
    /*presumably greater then 1*/
    val intervalLengthInt: Int =
      hiBound - loBound + 1
    /*mast be same type*/
    val threshold: Double = 1.0 / intervalLengthInt
    /*return value*/
    if (loBound == hiBound) {
      loBound
    } else {
      /*? 'floor' or 'truncate' ?*/
      loBound +
        (randomDouble / threshold)
        .toInt
    }
  }

  /*randomly return 'elemVal' as `pivot`*/
  def ChoosePivotRanomly(
                          sourceSeq: Array[Int],
                          //sourceSeqLength: Int
                          firstSeqIndex: Int,
                          lastSeqIndex: Int
                          ): Int = {
    if (sourceSeq.isEmpty) {
      /*return value*/
      /*assume that all values must be positive*/
      -1
    } else {
      /*return value*/
      sourceSeq.head
    }
  }

  /*input has only positive distinct integers*/
  def QuickSort(
                 unsorted: Array[Int],
                 unsortedLenght: Int
                 ): Array[Int] = {
    if (unsortedLenght <= 1) {
      /*return value*/
      unsorted
    } else {
      //val pivotIndex: Int =
      val pivot: Int =
        ChoosePivotAsHead(
                           sourceSeq = unsorted,
                           sourceSeqLenght = unsortedLenght
                         )
      val (part1, part2) =
        unsorted
        //.partition(_ <= unsorted(pivotIndex))
        .partition(_ <= pivot)

      /*recursion*/
      val part1Sorted =
        QuickSort(
                   /*? 'part1.head == pivot', so exclude 'pivot' ?*/
                   unsorted = part1.tail,
                   unsortedLenght = part1.tail.length
                 )
      val part2Sorted =
        QuickSort(
                   unsorted = part2,
                   unsortedLenght = part2.length
                 )
      /*return value*/
      //part1Sorted +: pivot ++ part2Sorted
      part1Sorted ++ (pivot +: part2Sorted)
    }

  }

  trait PivotRule

  case object FirstPivot extends PivotRule

  case object LastPivot extends PivotRule

  case object MedianPivot extends PivotRule

  case object RandomPivot extends PivotRule

  case class SortResults(sortedArray: Array[Int], comparisonsTotal: Int)

  def pivotParts(
                  sourceArray: Array[Int],
                  pivotIndex: Int
                  ): (Array[Int], Array[Int]) = {
    val pivot: Int =
      sourceArray(pivotIndex)
    /*
    DONE
    fix partition logic for each `pivotRule`
    * */
    /*has case for each pivotRule*/
    val (seqPartBeforePivot, seqPartPivotAndAfter): (Array[Int], Array[Int]) =
      sourceArray
      .splitAt(pivotIndex)
    val pivotingSequence: Array[Int] =
      seqPartBeforePivot ++ seqPartPivotAndAfter.tail
    /*return value*/
    //val (part1, part2) =
    pivotingSequence
    //.partition(_ <= unsorted(pivotIndex))
    .partition(_ <= pivot)
  }

  /*input has only positive distinct integers*/
  /* using three different pivot rule*/
  def QuickSortComparisons(
                            unsorted: Array[Int],
                            unsortedLenght: Int,
                            /*accumulator*/
                            comparisonsTotal: Int = 0,
                            pivotRule: PivotRule = FirstPivot
                            ): SortResults = {
    if (unsortedLenght <= 1) {
      /*return value*/
      //unsorted
      //0
      //unsortedLenght - 1
      SortResults(unsorted, comparisonsTotal)
    } else {
      val pivotIndex: Int =
      //val (pivot, pivotIndex): (Int, Int) =
        pivotRule match {
          case FirstPivot  =>
            ChooseFirstElementAsPivot(
                                       sourceSeq = unsorted,
                                       sourceSeqLenght =
                                         unsortedLenght
                                     )
          case LastPivot   =>
            ChooseLastElementAsPivot(
                                      sourceSeq = unsorted,
                                      sourceSeqLenght =
                                        unsortedLenght
                                    )
          case MedianPivot =>
            ChooseMedianOfThreeAsPivot(
                                        sourceSeq = unsorted,
                                        firstSeqIndex = 0,
                                        lastSeqIndex =
                                          unsortedLenght
                                            - 1
                                      )
          case RandomPivot =>
            //ChoosePivotRanomly(
            randomIntWithinInterval(
                                     //sourceSeq = unsorted,
                                     /*???*/
                                     //firstSeqIndex =
                                     0,
                                     /*???*/
                                     //lastSeqIndex =
                                     unsortedLenght - 1
                                   )
        }
      val pivot: Int =
        unsorted(pivotIndex)
      /*side effect*/
      pivotingTotalCheck += unsortedLenght - 1
      /*
      DONE
      fix partition logic for each `pivotRule`
      * */
      /*has case for each pivotRule*/
      /*val (seqPartBeforePivot, seqPartPivotAndAfter): (Array[Int],
      Array[Int]) =
        unsorted
        .splitAt(pivotIndex)
      val pivotingSequence: Array[Int] =
        seqPartBeforePivot ++ seqPartPivotAndAfter.tail*/
      val (part1, part2) =
      //unsorted
      //.tail
      //.init
      //pivotingSequence
      //.partition(_ <= unsorted(pivotIndex))
      //.partition(_ <= pivot)
        pivotParts(
                    sourceArray = unsorted,
                    pivotIndex: Int
                  )

      /*recursion*/
      //val part1Sorted =
      /*comparisonsTotal = comparisonsTotal + part1.tail.length - 1*/
      //val (part1Sorted, part1Comparisons): (Array[Int], Int) =
      val SortResults(part1Sorted, part1Comparisons): SortResults =
        QuickSortComparisons(
                              /*? 'part1.head == pivot', so exclude 'pivot' ?*/
                              unsorted =
                                //part1.tail,
                                part1,
                              unsortedLenght =
                                //part1.tail.length,
                                part1.length,
                              comparisonsTotal /*+
                                unsortedLenght - 1*/ ,
                              //part1.tail.length - 1
                              //part1.length - 1,
                              pivotRule = pivotRule
                            )
      //val part2Sorted =
      /*comparisonsTotal = comparisonsTotal + part2.length - 1*/
      //val (part2Sorted, part2Comparisons): (Array[Int], Int) =
      val SortResults(part2Sorted, part2Comparisons): SortResults =
        QuickSortComparisons(
                              unsorted = part2,
                              unsortedLenght = part2.length,
                              //comparisonsTotal +
                              unsortedLenght - 1,
                              //part2.length - 1,
                              pivotRule = pivotRule
                            )
      /*return value*/
      //part1Sorted +: pivot ++ part2Sorted
      SortResults(
                   part1Sorted ++ (pivot +: part2Sorted),
                   part1Comparisons + part2Comparisons
                   /*comparisonsTotal +
                   unsortedLenght - 1*/
                 )
    }

  }

  var comparisonsTotalCheck: Int = 0
  var pivotingTotalCheck: Int = 0

  /*input has only positive distinct integers*/
  /* using three different pivot rule*/
  def QuickSortWithInPlacePivotingComparisons(
                                               /*mutable collection, length -
                                                constant, content - not*/
                                               unsorted: Array[Int],
                                               //unsortedLenght: Int,
                                               startIndex: Int,
                                               endIndex: Int,
                                               /*accumulator*/
                                               comparisonsTotal: Int = 0,
                                               pivotRule: PivotRule = FirstPivot
                                               ): SortResults = {
    val unsortedLenght: Int =
      endIndex + 1 - startIndex

    if (unsortedLenght <= 1) {
      /*return value*/
      //unsorted
      //0
      //unsortedLenght - 1
      SortResults(unsorted, comparisonsTotal)
    } else {
      val pivotIndex: Int =
      //val (pivot, pivotIndex): (Int, Int) =
        pivotRule match {
          case FirstPivot  =>
            ChooseFirstElementAsPivot(
                                       sourceSeq = unsorted,
                                       sourceSeqLenght =
                                         unsortedLenght,
                                       startIndex = startIndex,
                                       endIndex = endIndex
                                     )
          case LastPivot   =>
            ChooseLastElementAsPivot(
                                      sourceSeq = unsorted,
                                      sourceSeqLenght =
                                        unsortedLenght,
                                      startIndex = startIndex,
                                      endIndex = endIndex
                                    )
          case MedianPivot =>
            ChooseMedianOfThreeAsPivot(
                                        sourceSeq = unsorted,
                                        /*???*/
                                        firstSeqIndex =
                                          startIndex,
                                        /*???*/
                                        lastSeqIndex =
                                          //unsortedLenght - 1
                                          endIndex
                                      )
          case RandomPivot =>
            //ChoosePivotRanomly(
            randomIntWithinInterval(
                                     //sourceSeq = unsorted,
                                     /*???*/
                                     //firstSeqIndex =
                                     startIndex,
                                     /*???*/
                                     //lastSeqIndex =
                                     //unsortedLenght - 1
                                     endIndex
                                   )

        }

      /*
      when
      unsorted.length == 2 or
      endIndex - startIndex
      pivoting with ? any rule ? completely sort array
      */
      /*after 'PivotingArrayInPlace' pivotIndex may change*/
      /*& must position pivot value in right ordered place in Array*/
      val pivotingResults: PivotingResults =
        PivotingArrayInPlace(
                              sourceArray = unsorted,
                              /*???*/
                              startingIndex = startIndex,
                              /*???*/
                              endingIndex =
                                //unsorted.length - 1,
                                //unsortedLenght - 1,
                                endIndex,
                              pivotIndex = pivotIndex
                            )
      /*side effect*/
      pivotingTotalCheck += endIndex - startIndex
      /*
      when
      there is
      a recursive call on a `subarray` of length 'm',
      you should
      simply add 'm−1' to your running `total of comparisons`.
      (This is because
      the pivot element is
      compared to
      each of the other 'm−1' elements
      in the subarray in this recursive call.)
       */
      val SortResults(/*part1Sorted*/ _, part1Comparisons): SortResults =
        if (
        /*at least two elements before pivot*/
          pivotingResults.pivotIndex - 1 > startIndex
        ) {
          /*side effect*/
          comparisonsTotalCheck +=
            pivotingResults
            .pivotIndex -
              startIndex
          /*recursion*/
          /*all before / less than pivot*/
          QuickSortWithInPlacePivotingComparisons(
                                                   unsorted =
                                                     unsorted,
                                                   startIndex =
                                                     startIndex,
                                                   endIndex =
                                                     pivotingResults.pivotIndex
                                                       - 1,
                                                   /*very subtle moment*/
                                                   //comparisonsTotal +
                                                   /*if same then pointless*/
                                                   pivotingResults
                                                   .pivotIndex -
                                                     endIndex +
                                                     endIndex -
                                                     startIndex,
                                                   pivotRule = pivotRule
                                                 )
        } else {
          SortResults(unsorted, comparisonsTotal)
        }

      val SortResults(/*part2Sorted*/ _, part2Comparisons): SortResults =
        if (
        /*at least two elements after pivot*/
          pivotingResults.pivotIndex + 1 < endIndex
        ) {
          /*side effect*/
          comparisonsTotalCheck +=
            endIndex -
              pivotingResults
              .pivotIndex
          /*recursion*/
          /*all after / greater than pivot*/
          QuickSortWithInPlacePivotingComparisons(
                                                   unsorted = unsorted,
                                                   startIndex =
                                                     pivotingResults.pivotIndex
                                                       + 1,
                                                   endIndex =
                                                     endIndex,
                                                   /*very subtle moment*/
                                                   //comparisonsTotal +
                                                   /*if same then pointless*/
                                                   endIndex - //startIndex
                                                     pivotingResults.pivotIndex,
                                                   pivotRule = pivotRule
                                                 )
        } else {
          SortResults(unsorted, comparisonsTotal)
        }

      /*return value*/
      SortResults(
                   /*sorted in place at this moment*/
                   unsorted,
                   /*? is 'comparisonsTotal' calculated twice ?*/
                   comparisonsTotal +
                     part1Comparisons + part2Comparisons //+
                   //endIndex - startIndex
                 )
    }

  }
}

/*
Question 2
GENERAL DIRECTIONS AND HOW TO GIVE US YOUR ANSWER:
See the first question.

DIRECTIONS FOR THIS PROBLEM:
>Compute the number of comparisons (as in Problem 1),
always using
the `final element` of the given array as
the `pivot element`.
Again,
be sure to implement the Partition subroutine exactly as
it is described in the video lectures.
Recall from the lectures that,
just before the main Partition subroutine,
you should
`exchange` the `pivot element`
(i.e., the `last element`)
with the `first element`.
 */

/*
Question 3
GENERAL DIRECTIONS AND HOW TO GIVE US YOUR ANSWER:
See the first question.

DIRECTIONS FOR THIS PROBLEM:
>Compute the number of comparisons (as in Problem 1),
using
the "median-of-three" pivot rule.
[The primary motivation behind this rule is
to do a little bit of extra work to
get much better performance on input arrays that are
nearly sorted or
reverse sorted.]
In more detail,
you should
choose the `pivot` as follows.
Consider
the `first`,
`middle`, and
`final` 'elements' of the given array.
(If the array has
`odd` 'length' it should be clear
what the "middle" element is;
for an array with `even` 'length' '2k',
use
the 'k-th' element as the "middle" element.
So
for the array '4 5 6 7',
the "middle" element is
the second one ---- '5' and not 6!)
Identify
which of these three elements is the `median`
(i.e., the one whose value is in between the other two), and
use this as your `pivot`.
As discussed in the first and
second parts of this programming assignment,
be sure
to implement Partition exactly
as described in the video lectures
(including
`exchanging` the pivot element with
the `first` element just before
the main `Partition subroutine`).

EXAMPLE:
For the input array
8 2 4 5 7 1
you would
consider
the first (8),
middle (4), and
last (1) elements;
since '4' is
the median of the set {1,4,8},
you would
use '4' as your `pivot element`.

SUBTLE POINT:
>A careful analysis would
  keep track of
  the `comparisons` made
  in identifying
  the `median` of the `three candidate` elements.
  You should NOT do this.
  That is,
  as in the previous two problems,
  you should
  simply add 'm−1' to
  your running total of comparisons
  every time you recurse on a subarray with length 'm'.
 */