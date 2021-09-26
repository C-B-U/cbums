import React, { PureComponent } from "react";

class StudySlideTag extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className="main__slide-tag">
          태그 <br />
          <form action="#" method="POST" name="study_tag">
            <input
              className="studymake_input"
              type="text"
              id="main__slide-tag-input"
              name="tag"
              autoComplete="off"
              placeholder="태그 클릭 시 삭제"
            />
            <button className="main__slide-tag-button" type="button">
              <p>+</p>
            </button>
            <ul className="main__slide-tag-list">
              <li className="main__slide-tag-list-item">#web</li>
              <li className="main__slide-tag-list-item">#스터디</li>
            </ul>
          </form>
        </div>
      </React.Fragment>
    );
  }
}

export default StudySlideTag;
