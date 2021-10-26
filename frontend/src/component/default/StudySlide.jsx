import { Swiper, SwiperSlide } from "swiper/react";
import React, { PureComponent } from "react";
import SlideBox from "./SlideBox";
import { Link } from "react-router-dom";
import SwiperCore, { Navigation } from "swiper";
import { FaArrowLeft, FaArrowRight } from "react-icons/fa";
import "swiper/components/navigation/navigation.scss";
import "../../css/slide.css";
import style from "../../css/default/default.module.css";
SwiperCore.use([Navigation]);

class StudySlide extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Swiper
          className={style["swiper_container"]}
          slidesPerView={3.1}
          slidesPerGroup={3.1}
          spaceBetween={24}
          navigation={{
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
          }}
          breakpoints={{
            1280: {
              slidesPerView: 5.5,
              slidesPerGroup: 5.5,
              spaceBetween: 50,
            },
            600: {
              slidesPerView: 4,
              slidesPerGroup: 4,
              spaceBetween: 30,
            },
          }}
        >
          <Link to="/">
            <SwiperSlide className={style.swiper_slide} key="study_1">
              <SlideBox contentName="CBUMS" Color="#bab843" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className={style.swiper_slide} key="study_2">
              <SlideBox contentName="Java" Color="#837d75" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className={style.swiper_slide} key="study_3">
              <SlideBox contentName="파이썬" Color="#f4c853" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className={style.swiper_slide} key="study_4">
              <SlideBox contentName="자료구조" Color="#ffb4c6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className={style.swiper_slide} key="study_5">
              <SlideBox contentName="C언어" Color="#54d6b6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className={style.swiper_slide} key="study_6">
              <SlideBox contentName="C++" Color="#c3a2f3" />
            </SwiperSlide>
          </Link>
        </Swiper>
        <div className={`swiper-button-prev ${style["button"]}`}>
          <FaArrowLeft />
        </div>
        <div className={`swiper-button-next ${style["button"]}`}>
          <FaArrowRight />
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlide;
