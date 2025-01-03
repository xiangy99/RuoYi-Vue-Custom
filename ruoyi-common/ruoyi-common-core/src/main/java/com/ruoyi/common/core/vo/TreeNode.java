package com.ruoyi.common.core.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {
    
    private Long id;
    
    private Long parentId;
    
    private String label;
    
    private List<TreeNode> children;
}