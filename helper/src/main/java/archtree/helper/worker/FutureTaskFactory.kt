package archtree.helper.worker

import java.util.concurrent.Executor

open class FutureTaskFactory constructor(private val defaultWorkerThread: Executor,
                                         private val defaultAfterExecuteThread: Executor) {

    fun create(): FutureTaskDoWork {
        return FutureTask(defaultWorkerThread, defaultAfterExecuteThread)
    }
}