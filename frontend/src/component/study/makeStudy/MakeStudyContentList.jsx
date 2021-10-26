import React, { PureComponent } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import SwiperCore, { Navigation } from "swiper";
import "swiper/components/navigation/navigation.scss";
import "../../../css/slide.css";
import "swiper/swiper.scss";
import style from "../../../css/study/study_make/study_make.module.css";
import MakeStudyInformation from "./MakeStudyInformation";
import MakeStudyExplanation from "./MakeStudyExplanation";
import MakeStudyTag from "./MakeStudyTag";
import MakeStudyButton from "./MakeStudyButton";
SwiperCore.use([Navigation]);

class MakeStudyContentList extends PureComponent {
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
                  <MakeStudyInformation />
                </div>
              </SwiperSlide>
              <SwiperSlide key="studymake_2" className={style.studymake_slide}>
                <div className={style["studymake_slide-wrap"]}>
                  <MakeStudyExplanation />
                </div>
              </SwiperSlide>
              <SwiperSlide key="studymake_3" className={style.studymake_slide}>
                <div className={style["studymake_slide-wrap"]}>
                  <MakeStudyTag />
                </div>
              </SwiperSlide>
            </Swiper>
          </div>
          <MakeStudyButton />
        </div>
      </React.Fragment>
    );
  }
}

export default MakeStudyContentList;
