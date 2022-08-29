const firebaseConfig = {
    apiKey: "AIzaSyBXKogkmWLf4RTkFa-RTcUzRHLzuB4G1o8",
    authDomain: "buildingcontructor.firebaseapp.com",
    databaseURL: "https://buildingcontructor-default-rtdb.firebaseio.com",
    projectId: "buildingcontructor",
    storageBucket: "buildingcontructor.appspot.com",
    messagingSenderId: "567901877180",
    appId: "1:567901877180:web:94f66657806c93779eb1b9",
    measurementId: "G-Y028159DYP"
};
firebase.initializeApp(firebaseConfig);
var dbRef = firebase.database();
var contactsRef = dbRef.ref('projects');

//load older conatcts as well as any newly added one...
contactsRef.on("child_added", function (snap) {
    console.log("added", snap.key, snap.val());
    $('#projects').append(contactHtmlFromObject(snap.val(), snap.key));
    $('#sliders-img').append(slidersHtmlFromObject(snap.val().images));
});
function slidersHtmlFromObject(array) {
    console.log(array);
    var htmls = `	<div class="rs-slider case-slider-style3 pt-120 md-pt-80">
    <div class="container">
        <div class="rs-carousel owl-carousel" data-loop="true" data-items="1" data-margin="0" data-autoplay="true" data-hoverpause="true" data-autoplay-timeout="5000" data-smart-speed="800" data-dots="false" data-nav="false" data-nav-speed="false" data-center-mode="false" data-mobile-device="1" data-mobile-device-nav="false" data-mobile-device-dots="false" data-ipad-device="1" data-ipad-device-nav="true" data-ipad-device-dots="false" data-ipad-device2="1" data-ipad-device-nav2="true" data-ipad-device-dots2="false" data-md-device="1" data-md-device-nav="true" data-md-device-dots="false">`;
    array.forEach(element => {
        htmls += `
        <div class="slider-img">
								<img src="${element}" alt="Slider">
							</div>
        `;
    });
    htmls += `</div>
    </div>
</div>`;
    return htmls;
}
function contactHtmlFromObject(p, k) {
    console.log(p);

    var html = '';
    html += `<div class="col-lg-6 col-md-6 mb-20" ng-repeat="p in projects">
                <div class="project-item">
                    <div class="project-img">
                        <img src="${p.images[0]}" alt="Images">
                    </div>
                    <div class="project-content">
                        <span class="category"><a onclick="detail(${k})">Dự án tiêu biểu
                                ${p.year}</a></span>
                        <h3 class="title"><a onclick="detail(${k})">${p.name}</a></h3>
                    </div>
                </div>
            </div>`;

    return html;
}
// <div class="container">
// 	<div class="rs-carousel owl-carousel"  data-loop="true" data-items="1" data-margin="0" data-autoplay="true" data-hoverpause="true" data-autoplay-timeout="5000" data-smart-speed="800" data-dots="false" data-nav="false" data-nav-speed="false" data-center-mode="false" data-mobile-device="1" data-mobile-device-nav="false" data-mobile-device-dots="false" data-ipad-device="1" data-ipad-device-nav="true" data-ipad-device-dots="false" data-ipad-device2="1" data-ipad-device-nav2="true" data-ipad-device-dots2="false" data-md-device="1" data-md-device-nav="true" data-md-device-dots="false">


// 		<div class="slider-img" ng-repeat="im in project1.images">
// 			<img src="{{im}}" alt="Slider">
// 		</div>
// 	</div>
// </div>





function detail(id) {
    //load older conatcts as well as any newly added one...
    switch (id) {
        case 1:
            {
                $("show1").show();
                $("show2").hide();
                $("show3").hide();
                $("show4").hide();
            }
            break;
        case 2:
            {

            }
            break;
        case 3:
            {

            }
            break;
        case 4:
            {

            }
            break;
        default:
            break;
    }

}
