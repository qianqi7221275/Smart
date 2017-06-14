/**
 * Created by oneapm on 2017/6/14.
 */
function processPostResult(result){

    if(result.result.code == '200'){
        result.success(result.result);
    }else if (result.result.code == '800'){
        result.warning(result.result);
    }else if(result.result.code == '500'){
        result.error(result.result);
    }
}