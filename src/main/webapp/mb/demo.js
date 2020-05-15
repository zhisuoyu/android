var count = 0

var page_example = {
    // pageNo: 1,
    // init: function () {
    //     for (var i = 0; i < 20; i++, count++) {
    //         var html = '<div  class="common-items">' +
    //             '<div class="common-usercontent">' +
    //             ' 項目' + count +
    //             '</div>' +
    //             '<div class="common-previewcontent">' +
    //             ' 內容' + count +
    //             ' </div> ' +
    //             ' </div>';
    //         $("#content").append(html);
    //     }
    // },
    cache: []
    ,
    loadContent: function () {
        for (var i = 0; i < 20; i++, count++) {
            var html = '<div  class="common-items">' +
                '<div class="common-usercontent">' +
                '項目' + count +
                '</div>' +
                '<div class="common-previewcontent">' +
                ' 內容' + count +
                ' </div> ' +
                ' </div>';
            $("#content").append(html);
            page_example.cache.push(html)
            console.log(page_example.cache.length)
            if (page_example.cache.length > 60) {
                $("#content").children().first().remove();
                page_example.cache.shift()

            }
        }
    }
}
$(function () {
    page_example.loadContent();
    $(window).scroll(function () {
        var mybody = document.body;
        //FF支持document.documentElement.scrollTop，chrome支持document.body.scrollTop
        if (mybody.scrollHeight - mybody.clientHeight <= (mybody.scrollTop | document.documentElement.scrollTop) + 200) {
            console.log('加载下一页');
            page_example.pageNo++;
            page_example.loadContent(page_example.pageNo)
        }
    });
});