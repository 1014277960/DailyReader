package wulinpeng.com.framework.base.net

/**
 *
 * @author wulinpeng
 * @datetime: 2019/4/6 8:40 PM
 * @description:
 */
class ApiConstant {

    companion object {
        const val IMG_BASE_URL = "http://statics.zhuishushenqi.com/"
        const val API_BASE_URL = "http://api.zhuishushenqi.com/"
        // 追书最热榜（周／月／总）
        val RANKING_HOT = arrayOf("54d42d92321052167dfb75e3", "564d820bc319238a644fb408", "564d8494fe996c25652644d2")
        // 读者存留率（周／月／总）
        val RANKING_STAY = arrayOf("564547c694f1c6a144ec979b", "564d898f59fd983667a5e3fa", "564d8a004a15bb8369d9e28d")
        // 追书完结榜（周／月／总）
        val RANKING_END = arrayOf("564eb878efe5b8e745508fde", "564eb12c3edb8b45511139ff", "564eea0b731ade4d6c509493")
        // 本周潜力榜
        val RANKING_POTENTIAL = arrayOf("54d42e72d9de23382e6877fb")
    }
}