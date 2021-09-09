import { Swiper, SwiperSlide } from "swiper/react";
import React, { PureComponent } from "react";
import SlideBox from "./SlideBox";
import { Link } from "react-router-dom";

class NoticeSlide extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Swiper
          className="swiper-container"
          slidesPerView={3.1}
          slidesPerGroup={3.1}
          spaceBetween={24}
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
            <SwiperSlide className="swiper-slide" key="notice_1">
              <SlideBox studyName="진행사항 업로드" Color="#bab843" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper-slide" key="notice_2">
              <SlideBox studyName="동아리　 가입방법" Color="#837d75" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper-slide" key="notice_3">
              <SlideBox studyName="스터디　 가입방법" Color="#f4c853" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper-slide" key="notice_4">
              <SlideBox studyName="규칙　　　" Color="#ffb4c6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper-slide" key="notice_5">
              <SlideBox studyName="공지사항1" Color="#54d6b6" />
            </SwiperSlide>
          </Link>
          <Link to="/">
            <SwiperSlide className="swiper-slide" key="notice_6">
              <SlideBox studyName="공지사항2" Color="#c3a2f3" />
            </SwiperSlide>
          </Link>
        </Swiper>
      </React.Fragment>
    );
  }
}

export default NoticeSlide;