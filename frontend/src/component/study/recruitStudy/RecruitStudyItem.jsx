import React, { PureComponent } from "react";
import style from "../../../css/study/study_recruit/study_recruit.module.css";
import { Link } from "react-router-dom";
import RecruitStudyStatus from "./RecruitStudyStatus";
class RecruitStudyItem extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.post_wrap}>
          <div className={style.post_title}>
            <RecruitStudyStatus recruit={this.props.recruit} />
            <div className={style.title_text}>
              <Link to={{pathname:`/study/recruit/${this.props.projectId}`,
                state: {
                      recruit:this.props.recruit,
                      name:this.props.name,
                      producer:this.props.producer,
                      registerDatetime:this.props.registerDatetime,
                      registerNumber:this.props.registerNumber,
              }
            }}>
                {this.props.name}
              </Link>
            </div>
          </div>
          <div className={style.post_tags}>
            <ul className={style["main__slide-tag-list"]}>
              <li className={style["main__slide-tag-list-item"]} key="1">
                스터디
              </li>
              <li className={style["main__slide-tag-list-item"]} key="2">
                JAVA
              </li>
            </ul>
          </div>
          <div className={style.post_info}>
            <span className={style.info_name}>{this.props.producer}</span>
            <span className={style.info_date}>
              {this.props.registerDatetime}
            </span>
          </div>
          <div className={style.detail_wrap}>
            <div className={style.comment_wrap}>
              <div className={style.comment_number}>0</div>
              <div style={{ fontSize: "0.8em", color: "grey" }}>댓글</div>
            </div>

            <div className={style.head_count}>
              모집인원: {this.props.registerNumber}
            </div>
          </div>
          <div className={style.partition} />
        </div>
      </React.Fragment>
    );
  }
}

export default RecruitStudyItem;
