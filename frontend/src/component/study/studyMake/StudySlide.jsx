import React, { PureComponent } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation } from "swiper";
import "swiper/components/navigation/navigation.scss";
import "../../../css/slide.css";
import "swiper/swiper.scss";
import style from "../../../css/study/study_make/study_make.module.css";
import StudySlideButton from "./StudySlideButton";
import StudySlideTag from "./StudySlideTag";
import StudySlideExplanation from "./StudySlideExplanation";
import StudySlideInformation from "./StudySlideInformation";
SwiperCore.use([Navigation]);

class StudySlide extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["slide-wrap"]}>
          <div className={style["slide-container"]}>
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
              <SwiperSlide key="studymake_1" className={style.studymake_slide}>
                <div className={style["studymake_slide-wrap"]}>
                  <StudySlideInformation />
                </div>
              </SwiperSlide>
              <SwiperSlide key="studymake_2" className={style.studymake_slide}>
                <div className={style["studymake_slide-wrap"]}>
                  <StudySlideExplanation />
                </div>
              </SwiperSlide>
              <SwiperSlide key="studymake_3" className={style.studymake_slide}>
                <div className={style["studymake_slide-wrap"]}>
                  <StudySlideTag />
                </div>
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
