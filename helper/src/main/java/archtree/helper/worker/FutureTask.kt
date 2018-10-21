package archtree.helper.worker

import java.util.concurrent.Executor

open class FutureTask
constructor(private var workerThread: Executor,
            private var afterExecuteThread: Executor) : FutureTaskDoWork, FutureTaskDoAfter {

    private val workerBlockList = ArrayList<() -> Unit>()
    private var postWorkerBlock: () -> Unit? = { }

    override fun doWork(runnable: () -> Unit): FutureTaskDoWork {
        workerBlockList.add(runnable)
        return this
    }

    override fun doAfter(runnable: () -> Unit): FutureTaskDoAfter {
        postWorkerBlock = runnable
        return this
    }

    override fun withWorkerThread(executor: Executor): FutureTaskDoAfter {
        workerThread = executor
        return this
    }

    override fun withAfterWorkThread(executor: Executor): FutureTaskDoAfter {
        afterExecuteThread = executor
        return this
    }

    override fun execute() {
        workerThread.execute {
            workerBlockList.forEach {
                it()
            }

            afterExecuteThread.execute {
                postWorkerBlock()
            }
        }
    }
}