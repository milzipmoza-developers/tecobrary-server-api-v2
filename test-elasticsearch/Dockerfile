ARG ELK_VERSION="7.5.0"

# https://github.com/elastic/elasticsearch-docker
FROM docker.elastic.co/elasticsearch/elasticsearch:${ELK_VERSION}

COPY ./config/elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml
COPY ./plugins /plugins

# Add your elasticsearch plugins setup here
# Example: RUN elasticsearch-plugin install analysis-icu

# 초성, 자동완성 검색 커스텀 플러그인을 설치한다.
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install file:///plugins/elasticsearch-hangul-jamo-analyzer-7.5.0.zip
# 한국어 형태소 분석기 플러그인 '노리'를 설치한다.
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch analysis-nori