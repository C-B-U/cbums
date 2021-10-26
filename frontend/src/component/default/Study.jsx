import React, { PureComponent } from "react";
import ArticleTitle from "./ArticleTitle";
import StudySlide from "./StudySlide";
import style from "../../css/default/default.module.css";

class Study extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__study"]}>
          <ArticleTitle title="Recruiting Study" />
          <StudySlide />
        </div>
      </React.Fragment>
    );
  }
}

export default Study;
