import React, { PureComponent } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation } from "swiper";
import "swiper/components/navigation/navigation.scss";
import "../../../css/slide.css";
import StudySlideButton from "./StudySlideButton";
import StudySlideTag from "./StudySlideTag";
import StudySlideExplanation from "./StudySlideExplanation";
import StudySlideInformation from "./StudySlideInformation";
SwiperCore.use([Navigation]);

class StudySlide extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="slide-wrap">
          <div className="slide-container">
            <Swiper
              slidesPerView={1}
              slidesPerGroup={1}
              mousewheel={false}
              touchRatio={false}
              navigation={{
                nextEl: ".swiper-button-next",
                prevEl: ".swiper-button-prev",
              }}
            >
              <SwiperSlide key="studymake_1" className="studymake_slide">
                <StudySlideInformation />
              </SwiperSlide>
              <SwiperSlide key="studymake_2" className="studymake_slide">
                <StudySlideExplanation />
              </SwiperSlide>
              <SwiperSlide key="studymake_3" className="studymake_slide">
                <StudySlideTag />
              </SwiperSlide>
            </Swiper>
          </div>
          <StudySlideButton />
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlide;
